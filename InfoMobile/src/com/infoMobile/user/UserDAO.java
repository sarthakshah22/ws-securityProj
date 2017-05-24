package com.infoMobile.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO {

	public List<User> getAllUser(){
	      List<User> userList = null; 
	      try { 
	         File file = new File("D:/ASI/Targets/EclipseWorkspaceForTarget/InfoMobile/user.dat"); 
	         if (!file.exists()) { 
	            User user = new User("admin", "admin", "admin@admin"); 
	            userList = new ArrayList<User>(); 
	            userList.add(user); 
	            saveUserList(userList); 
	         } 
	         else{ 
	            FileInputStream fis = new FileInputStream(file); 
	            ObjectInputStream ois = new ObjectInputStream(fis); 
	            userList = (List<User>) ois.readObject(); 
	            ois.close(); 
	         } 
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      } catch (ClassNotFoundException e) { 
	         e.printStackTrace(); 
	      }   
	      return userList; 
	   
		
	}
	
	   public int addUser(User user){
		   int response=1;
		   List<User> userList = getAllUser();
		   if(userList!=null){
			   Iterator<User> itr = userList.iterator();
			   while(itr.hasNext()){
				User obj = itr.next();
				System.out.println("obj "+obj);
				if(obj.getUserID().equals(user.getUserID())){
					userList.remove(obj); 
				}
			   } 
			   userList.add(user);
				response=0;
				saveUserList(userList);
		   }
		   return response;
	   }
	   
	   private void saveUserList(List<User> userList){ 
		      try { 
		         File file = new File("D:/ASI/Targets/EclipseWorkspaceForTarget/InfoMobile/user.dat"); 
		         FileOutputStream fos;  
		         fos = new FileOutputStream(file); 
		         ObjectOutputStream oos = new ObjectOutputStream(fos); 
		         oos.writeObject(userList); 
		         oos.close(); 
		      } catch (FileNotFoundException e) { 
		         e.printStackTrace(); 
		      } catch (IOException e) { 
		         e.printStackTrace(); 
		      } 
		   } 
}
