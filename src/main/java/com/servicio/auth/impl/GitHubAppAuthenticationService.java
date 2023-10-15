	package com.servicio.auth.impl;
	
	
	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modelo.GitHubUsuario;
import com.servicio.ConfigLoader;

public class GitHubAppAuthenticationService {


    private static GitHubAppAuthenticationService instance;

    private GitHubAppAuthenticationService() {
       
    }

    public static synchronized GitHubAppAuthenticationService getInstance() {
        if (instance == null) {
            instance = new GitHubAppAuthenticationService();
        }
        return instance;
    }

	public GitHubUsuario autenticarConToken(String token) {
		try {
			String accessToken = getAccessToken(token);
			System.out.println("accessToken:"+accessToken);
			try {
				GitHubUsuario usuario = getUserGitHubUsuario(accessToken);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    public String getAccessToken(String code) throws IOException {
    	// Validación inicial del código
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Código de autorización no válido.");
        }
        // URL del endpoint de GitHub para obtener el token de acceso
        String url = "https://github.com/login/oauth/access_token";
        
        // Configura los parámetros que serán enviados en la solicitud POST
        Map<Object, Object> data = new LinkedHashMap<>();
        data.put("client_id",  ConfigLoader.getInstance().getClientId());
        data.put("client_secret",  ConfigLoader.getInstance().getClientSecret());
        data.put("code", code);  // el 'code' que recibiste en el callback
        
        // Crea y configura la conexión HTTP
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json"); // Para que la respuesta sea JSON
        con.setDoOutput(true);
        
        // Escribe los parámetros en el cuerpo de la solicitud
        // Usar constantes para el charset
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = getParamsString(data).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        
        
        // Procesa la respuesta de GitHub
        int responseCode = con.getResponseCode();
        InputStream responseStream = (responseCode == 200) ? con.getInputStream() : con.getErrorStream();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream))) {
            String response = bufferedReader.readLine();
            System.out.println(response);
            String accessToken = extractTokenFromResponse(response); 
            return accessToken;
        }
    }


    private String getParamsString(Map<Object, Object> params) {
        return params.entrySet().stream()
            .map(entry -> {
                try {
                    return URLEncoder.encode(entry.getKey().toString(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Error while encoding URL parameters", e);
                }
            })
            .collect(Collectors.joining("&"));
    }
    
    private String extractTokenFromResponse(String response) {

    	 JsonParser jsonParser = new JsonParser();
    	    JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
    	    
    	    if (jsonObject.has("error")) {
    	        // Puedes lanzar una excepción o manejar el error de otra manera
    	        throw new RuntimeException("Error in response: " + jsonObject.get("error_description").getAsString());
    	    }

    	    return jsonObject.get("access_token").getAsString();    }


    public GitHubUsuario getUserGitHubUsuario(String accessToken) throws Exception {
    	URL url = new URL("https://api.github.com/user");
    	HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	con.setRequestMethod("GET");
    	con.setRequestProperty("Authorization", "Bearer " + accessToken);

    	int responseCode = con.getResponseCode();
    	System.out.println("Response Code :: " + responseCode);

    	if (responseCode == HttpURLConnection.HTTP_OK) { //success
    	    BufferedReader in = new BufferedReader(new InputStreamReader(
    	            con.getInputStream()));
    	    String inputLine;
    	    StringBuffer response = new StringBuffer();

    	    while ((inputLine = in.readLine()) != null) {
    	        response.append(inputLine);
    	    }
    	    in.close();
    	    GitHubUsuario usuario =transformFromJSON(response);
    	    // Print result
    	    System.out.println(response.toString());
    	    return usuario;
    	} else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
    	    // Manejo de error de autorización (401)
    	    // Notificar al usuario o tomar acciones correctivas
    		
    		
    	} else {
    	    // Manejo de otras respuestas
    	    System.err.println("Unexpected response code: " + responseCode);
    	}
		return null;
    }

	private GitHubUsuario transformFromJSON(StringBuffer response) {
		  Gson gson = new Gson();
		  return gson.fromJson(response.toString(), GitHubUsuario.class);
	}



}