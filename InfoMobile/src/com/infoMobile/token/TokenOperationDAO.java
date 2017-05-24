package com.infoMobile.token;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infoMobile.DBConnection.DBConnectivity;

public class TokenOperationDAO {
	DBConnectivity connectDB = new DBConnectivity();
	
	public void saveOrUpdateToken(Token token){
		Session session = connectDB.getConnection();
		Transaction transaction = session.beginTransaction();  
		session.saveOrUpdate(token);
		transaction.commit();
		connectDB.closeConnection(session);
	}
	
	public boolean verifyToken(String token) throws ParseException{
		Session session = connectDB.getConnection();
		 boolean isTokenExist = false;
		 Token tokenObj;
			Transaction transaction = session.beginTransaction();  
			tokenObj = (Token) session.get(Token.class, token);
			transaction.commit();
			//todo verify it
			Date curreDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
			
			System.out.println(tokenObj.getTokenDate()+"  "+tokenObj.getTokenId());
			
			Date currentFormatDate = format.parse(curreDate.toString());
			Date tokenFormatDate = format.parse(tokenObj.getTokenDate());
			long diff = currentFormatDate.getTime() - tokenFormatDate.getTime();
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);


			System.out.println("current date "+curreDate);
			System.out.println("toeknDate "+tokenObj.getTokenDate());
			System.out.println("diffMinutes "+diffMinutes);
			
			 if(token.equalsIgnoreCase(tokenObj.getTokenId()) && diffDays==0 && diffHours==0 && diffMinutes<2){
				   isTokenExist=true;
			   }
		 connectDB.closeConnection(session);
		 return isTokenExist;
	}
}
