package com.infoMobile.authentication;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.infoMobile.user.User;
import com.infoMobile.user.UserDAO;

import Decoder.BASE64Decoder;

public class AuthenticationService {
	public boolean authenticate(String authString){
		boolean isAuthenticate = false;
		String decodedAuth = "";
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		//Decode the data back to original string
		byte[] bytes = null;
		try {
			bytes = new BASE64Decoder().decodeBuffer(authInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		decodedAuth = new String(bytes);
		System.out.println(decodedAuth);
		String[] credentials = decodedAuth.split(":");
		String credentail = credentials[0];
		String code = credentials[1];
		
		if(credentail.equalsIgnoreCase("InfoMobileAuthCode") && code.equalsIgnoreCase("000")){
			isAuthenticate = true;
		}
		return isAuthenticate;
	}
}
