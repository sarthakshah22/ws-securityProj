package com.infoMobile.user;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.infoMobile.DBConnection.DBConnectivity;
import com.infoMobile.token.Token;
import com.infoMobile.token.TokenDAO;
import com.infoMobile.token.TokenOperationDAO;


@Path("/UserService") 

public class UserService {
	private static String SUCCESS_RESULT = "success";
	private static String FAILURE_RESULT = "Failure";
	private static String INVALID_TOKEN = "InvalidToken";
	//UserDAO userDao = new UserDAO();  
	UserOperationDAO userOperationDAO = new UserOperationDAO(); 
	TokenOperationDAO tokenOperationDAO = new TokenOperationDAO(); 
	TokenDAO tokenDAO = new TokenDAO();
	
	@GET 
	@Path("/users") 
	@Produces({MediaType.APPLICATION_JSON}) 
	public String getUsers(@Context HttpServletRequest request) throws ParseException{ 
		 String token = request.getHeader("X_CSRF_TOKEN");
		 JSONArray jsonArray = new JSONArray();
		 JSONObject User = new JSONObject();
		boolean isTokenVerified = tokenOperationDAO.verifyToken(token);
		if(isTokenVerified){
			Token tokenObj = new Token(token,new Date().toString());
			tokenOperationDAO.saveOrUpdateToken(tokenObj);
			List<User> userList = userOperationDAO.getAllUser(); 
			Iterator<User> itr = userList.iterator();
			while(itr.hasNext()){
				User user = itr.next();
				JSONObject obj = new JSONObject();
				obj.put("ID", user.getUserID());
				obj.put("Email", user.getEmail());
				obj.put("Password", user.getPassword());
				jsonArray.add(obj);
			}
			User.put("response", jsonArray);
		}else{
			User.put("response", INVALID_TOKEN);
		}
		return User.toJSONString();
	}  

	@GET 
	@Path("/getAllUser") 
	@Produces(MediaType.APPLICATION_XML) 
	public List<User> getAllUser(@Context HttpServletRequest request){ 
		 return userOperationDAO.getAllUser(); 
	}  
	
	@POST
	@Path("/addUsers") 
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createUsers(@FormParam("userId") String userId
			,@FormParam("password") String password
			,@FormParam("email") String email,@Context HttpServletRequest request) throws ParseException{ 
		System.out.println(userId+" "+password+" "+email);
		String token = request.getHeader("X_CSRF_TOKEN");
		boolean isTokenVerified = tokenOperationDAO.verifyToken(token);
		if(isTokenVerified && userId!=null && password!=null && email!=null){
			Token tokenObj = new Token(token,new Date().toString());
			tokenOperationDAO.saveOrUpdateToken(tokenObj);
			User user = new User(userId,password,email);
			userOperationDAO.saveOrUpdate(user);
			return UserService.SUCCESS_RESULT; 
		}else{
			return UserService.INVALID_TOKEN; 
		}
		
	}
	
	@POST
	@Path("/deleteUsers") 
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteUsers(@FormParam("userId") String userId
		,@Context HttpServletRequest request) throws ParseException{ 
		System.out.println(userId);
		String token = request.getHeader("X_CSRF_TOKEN");
		boolean isTokenVerified = tokenOperationDAO.verifyToken(token);
		if(isTokenVerified && userId!=null){
			Token tokenObj = new Token(token,new Date().toString());
			tokenOperationDAO.saveOrUpdateToken(tokenObj);
			User user = new User(userId);
			userOperationDAO.deleteUser(user);
			return UserService.SUCCESS_RESULT; 
		}else{
			return UserService.INVALID_TOKEN; 
		}
		
	}
	
	@POST
	@Path("/login") 
	@Produces({"text/html", "application/json"})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String authenticateAndLoginUsers(@FormParam("userId") String userId,
			@FormParam("password") String password, 
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest request){
		
	 if(userId.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
		 HttpSession session = request.getSession();
		 session.setAttribute("UserType", "admin");
		 
		 UUID uuid = UUID.randomUUID();
		 String randomUUIDString = uuid.toString();
		 
		 Token tokenObj = new Token(randomUUIDString,new Date().toString());
		 tokenOperationDAO.saveOrUpdateToken(tokenObj);
		 JSONObject obj = new JSONObject();
		 obj.put("response", "success");
		 obj.put("token", randomUUIDString);
		 
		return obj.toJSONString();
	 }
			return "your detail are "; 
	}
}
