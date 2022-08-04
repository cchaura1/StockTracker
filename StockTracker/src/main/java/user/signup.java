package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/signup")
public class signup {

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	//Do user signup work here
	//http://localhost:8080/StockTracker/user/signup
	public Response userSignup(String requestBody) {
		
		JSONObject jsonObj = new JSONObject(requestBody);
		
		String firstname = (String) jsonObj.get("firstname");
		String lastname = (String) jsonObj.get("lastname");
		String email = (String) jsonObj.get("email");
		String password = (String) jsonObj.get("password");
		
		
		Connection connection;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3508765";
			String dbusername = "sql3508765";
			String dbpassword = "DAwZbdY5HS";
			connection = DriverManager.getConnection(
					dbURL, dbusername, dbpassword);
			Statement statement = connection.createStatement();
			
			System.out.println("Request : "+firstname + " | "+lastname + " | "+email+ " | "+password);
			
			String checkUser = "SELECT * FROM stockuser WHERE email = '"+email+"'";			
			ResultSet rs = statement.executeQuery(checkUser);
		
			if(!rs.isBeforeFirst()) {			
				System.out.println("Creating a new user");
				String userSql = "INSERT INTO stockuser (firstname,lastname,email,password) VALUES ('"+firstname+"', '"+lastname+"', '"+email+"', '"+password+"')";
				statement.executeUpdate(userSql);
				return Response.status(201).entity("User registered succesfully").build();		
				
			}else {
				System.out.println("User already exist");
				return Response.status(200).entity("User already exist").build();
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity("Unable to create a new user").build();
		}
		
		
	}

}
