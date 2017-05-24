package com.infoMobile.user;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infoMobile.DBConnection.DBConnectivity;

public class UserOperationDAO {

	DBConnectivity connectDB = new DBConnectivity();

	public void saveOrUpdate(User user){
		Session session = connectDB.getConnection();
		Transaction transaction = session.beginTransaction();  
		session.saveOrUpdate(user);
		transaction.commit();
		connectDB.closeConnection(session);
	}

	public void deleteUser(User user){
		Session session = connectDB.getConnection();
		Transaction transaction = session.beginTransaction();  
		session.delete(user);
		transaction.commit();
		connectDB.closeConnection(session);
	}
	
	public List<User> getAllUser(){
		Session session = connectDB.getConnection();
		List<User> userList = null; 
		Transaction transaction = session.beginTransaction();  
		userList = (List<User>)session.createCriteria(User.class).list();
		transaction.commit();
		connectDB.closeConnection(session);
		return userList;
	}

}
