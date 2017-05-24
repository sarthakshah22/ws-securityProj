package com.infoMobile.DBConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DBConnectivity {
	static Session session;
	
	public Session getConnection(){
		if(session!=null && session.isOpen()){
			return session;
		}
		else{
			return getHibernateSession();
		}
		
	}
	private static Session getHibernateSession(){
		 //creating configuration object  
	    Configuration cfg=new Configuration();  
	    cfg.configure("com/infoMobile/DBConnection/hibernate.cfg.xml");//populates the data of the configuration file  
	      
	    //creating seession factory object  
	    SessionFactory factory=cfg.buildSessionFactory();  
	      
	    //creating session object  
	    Session session=factory.openSession();  
	    return session;
	}
	
	public void closeConnection(Session session){
		if(session.isOpen()){
			session.close();
		}
	}
}
