package com.infoMobile.token;

import java.io.Serializable;
import java.util.Date;

public class Token implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String tokenId; 
	private String tokenDate;
	
	public String getTokenId() {
		return tokenId;
	}
	public Token(String tokenId) {
		super();
		this.tokenId = tokenId;
	}
	public Token() {
		super();
	}
	public Token(String tokenId, String tokenDate) {
		super();
		this.tokenId = tokenId;
		this.tokenDate = tokenDate;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getTokenDate() {
		return tokenDate;
	}
	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", tokenDate=" + tokenDate + "]";
	}
	public void setTokenDate(String tokenDate) {
		this.tokenDate = tokenDate;
	}
	 
	
}
