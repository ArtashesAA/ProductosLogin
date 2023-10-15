package com.servicio.auth.impl;

import com.modelo.BaseDatosUsuario;
import com.servicio.auth.ServicioAutenticacionCredenciales;

public class DatabaseAuthenticationService implements ServicioAutenticacionCredenciales {
	
	
	  private static DatabaseAuthenticationService instance;

	    private DatabaseAuthenticationService() {
	        // Constructor privado para evitar la creación de múltiples instancias.
	    }
	    
	    public static synchronized DatabaseAuthenticationService getInstance() {
	        if (instance == null) {
	            instance = new DatabaseAuthenticationService();
	        }
	        return instance;
	    }

		@Override
		public BaseDatosUsuario autenticarConCredenciales(String usuario, String contraseña) {
			// TODO Auto-generated method stub
			return null;
		}
	    
	    

}
