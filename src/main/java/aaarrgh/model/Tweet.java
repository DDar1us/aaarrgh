package aaarrgh.model;

import java.util.Date;

public class Tweet {
	
	private Integer idtweet;
	private String tweet;
	private Integer idusuario;
	private Date now;

	public java.sql.Timestamp getNow() {
		return new java.sql.Timestamp(now.getTime());
	}

	public void setNow(Date now) {
		this.now = now;
	}

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
