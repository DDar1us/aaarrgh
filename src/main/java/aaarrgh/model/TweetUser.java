package aaarrgh.model;

public class TweetUser {
	private Tweet tweet;
	private String usuario;
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public Tweet getTweet() {
		return tweet;
	}
	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setMensaje(String mensaje2) {
		this.mensaje = mensaje2;
		
	}
	
}
