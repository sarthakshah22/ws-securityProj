package testClient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.infoMobile.user.User;

public class TestHibernateConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //creating configuration object  
		
	    Configuration cfg=new Configuration();  
	    cfg.configure("com/infoMobile/DBConnection/hibernate.cfg.xml");//populates the data of the configuration file  
	      
	    //creating seession factory object  
	    SessionFactory factory=cfg.buildSessionFactory();  
	      
	    //creating session object  
	    Session session=factory.openSession();  
	      
	    //creating transaction object  
	    Transaction t=session.beginTransaction();  
	          
	    User e1=new User();  
	   e1.setEmail("test@test");
	   e1.setPassword("test123");
	   e1.setUserID("test1");
	      
	    session.persist(e1);//persisting the object  
	      
	    t.commit();//transaction is commited  
	    session.close();  
	      
	    System.out.println("successfully saved");  
	}

}
