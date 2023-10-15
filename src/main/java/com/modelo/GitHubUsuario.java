package com.modelo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class GitHubUsuario  implements Serializable{
	
	private static final long serialVersionUID = 1L;


	/**
	 * La anotación @SerializedName es utilizada en la deserialización y serialización
	 *  de objetos en JSON usando la biblioteca Gson en Java. 
	 *  Esta anotación indica a Gson cuál es la clave correspondiente en el JSON 
	 *  para un campo particular en la clase Java.
	 *  
	 *  Cuando defines un campo en tu clase Java y quieres mapearlo a una clave específica 
	 *  en tu objeto JSON, 
	 *  utilizas @SerializedName para especificar el nombre exacto de la clave en el JSON.

     Por ejemplo, en la clase GitHubUsuario     
     JSON
     {
          "login": "profeInformatica101"
	} */
	   @SerializedName("login")
	    private String login;

	    @SerializedName("id")
	    private int id;

	    @SerializedName("url")
	    private String url;

	    @SerializedName("html_url")
	    private String htmlUrl;


	    @SerializedName("name")
	    private String name;

	    @SerializedName("company")
	    private String company;

	    @SerializedName("blog")
	    private String blog;

	    @SerializedName("location")
	    private String location;

	    @SerializedName("email")
	    private String email;

	    @SerializedName("hireable")
	    private String hireable;

	    @SerializedName("bio")
	    private String bio;

	    @SerializedName("twitter_username")
	    private String twitterUsername;

	    @SerializedName("public_repos")
	    private int publicRepos;

	    @SerializedName("public_gists")
	    private int publicGists;

	    @SerializedName("followers")
	    private int followers;

	    @SerializedName("following")
	    private int following;

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}



		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHtmlUrl() {
			return htmlUrl;
		}

		public void setHtmlUrl(String htmlUrl) {
			this.htmlUrl = htmlUrl;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getBlog() {
			return blog;
		}

		public void setBlog(String blog) {
			this.blog = blog;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getHireable() {
			return hireable;
		}

		public void setHireable(String hireable) {
			this.hireable = hireable;
		}

		public String getBio() {
			return bio;
		}

		public void setBio(String bio) {
			this.bio = bio;
		}

		public String getTwitterUsername() {
			return twitterUsername;
		}

		public void setTwitterUsername(String twitterUsername) {
			this.twitterUsername = twitterUsername;
		}

		public int getPublicRepos() {
			return publicRepos;
		}

		public void setPublicRepos(int publicRepos) {
			this.publicRepos = publicRepos;
		}

		public int getPublicGists() {
			return publicGists;
		}

		public void setPublicGists(int publicGists) {
			this.publicGists = publicGists;
		}

		public int getFollowers() {
			return followers;
		}

		public void setFollowers(int followers) {
			this.followers = followers;
		}

		public int getFollowing() {
			return following;
		}

		public void setFollowing(int following) {
			this.following = following;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "GitHubUsuario [login=" + login + ", id=" + id + ", url=" + url + ", htmlUrl=" + htmlUrl + ", name="
					+ name + ", company=" + company + ", blog=" + blog + ", location=" + location + ", email=" + email
					+ ", hireable=" + hireable + ", bio=" + bio + ", twitterUsername=" + twitterUsername
					+ ", publicRepos=" + publicRepos + ", publicGists=" + publicGists + ", followers=" + followers
					+ ", following=" + following + "]";
		}

	
		
		
}

	