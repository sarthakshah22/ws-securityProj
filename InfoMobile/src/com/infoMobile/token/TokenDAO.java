package com.infoMobile.token;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.infoMobile.user.User;

public class TokenDAO {/*
	public List<Token> getToken(){
	      List<Token> tokenList = null; 
	      try { 
	         File file = new File("D:/ASI/Targets/EclipseWorkspaceForTarget/InfoMobile/token.dat"); 
	         if (!file.exists()) { 
//	            Token token = new Token("admin",new Date());
	            tokenList = new ArrayList<Token>(); 
	            tokenList.add(token); 
	            saveToken(tokenList); 
	         } 
	         else{ 
	            FileInputStream fis = new FileInputStream(file); 
	            ObjectInputStream ois = new ObjectInputStream(fis); 
	            tokenList = (List<Token>) ois.readObject(); 
	            ois.close(); 
	         } 
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      } catch (ClassNotFoundException e) { 
	         e.printStackTrace(); 
	      }   
	      return tokenList; 
	   
		
	}
	
	   public void saveToken(List<Token> tokenList){ 
		      try { 
		         File file = new File("D:/ASI/Targets/EclipseWorkspaceForTarget/InfoMobile/token.dat"); 
		         FileOutputStream fos;  
		         fos = new FileOutputStream(file); 
		         ObjectOutputStream oos = new ObjectOutputStream(fos); 
		         oos.writeObject(tokenList); 
		         oos.close(); 
		      } catch (FileNotFoundException e) { 
		         e.printStackTrace(); 
		      } catch (IOException e) { 
		         e.printStackTrace(); 
		      } 
		   } 
	   
	   public boolean verifyToken(String token) throws ParseException{
		   boolean isTokenExist = false;
		   List<Token> tokenList = getToken();
		   Iterator<Token> itr = tokenList.iterator();
		   while(itr.hasNext()){
			   Token tokenObj = itr.next();
			   Date curreDate = new Date();
			   SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
			 
				Date currentFormatDate = format.parse(curreDate.toString());
				Date tokenFormatDate = format.parse(tokenObj.getTokenDate().toString());
				long diff = currentFormatDate.getTime() - tokenFormatDate.getTime();
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				
			 
			 System.out.println("current date "+curreDate);
			 System.out.println("toeknDate "+tokenObj.getTokenDate());
			 System.out.println("diffMinutes "+diffMinutes);
			 
			   if(token.equalsIgnoreCase(tokenObj.getTokenId()) && diffDays==0 && diffHours==0 && diffMinutes<20){
				   isTokenExist=true;
			   }
			   System.out.println(tokenObj.getTokenId()+"  "+tokenObj.getTokenDate());
		   }
		   return isTokenExist ;
	   }
*/}
