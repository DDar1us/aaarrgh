package aaarrgh.model;

public class TweetUser {
	private Tweet tweet;
	private String usuario;
	private String mensaje;
	private Integer cantidadDeSeguidores;
	private Integer cantidadAquienesSigo;
	
	public Integer getCantidadDeSeguidores() {
		return cantidadDeSeguidores;
	}

	public void setCantidadDeSeguidores(Integer cantidadDeSeguidores) {
		this.cantidadDeSeguidores = cantidadDeSeguidores;
	}

	public Integer getCantidadAquienesSigo() {
		return cantidadAquienesSigo;
	}

	public void setCantidadAquienesSigo(Integer cantidadAquienesSigo) {
		this.cantidadAquienesSigo = cantidadAquienesSigo;
	}

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
