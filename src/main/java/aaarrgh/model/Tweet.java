package aaarrgh.model;

public class Tweet {
	
	private Integer idtweet;
	private String tweet;
	private Integer idusuario;

	public Tweet() {
		super();
	}

	public Integer getIdTweet() {
		return idtweet;
	}

	public void setIdTweet(Integer idtweet) {
		this.idtweet = idtweet;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

}
