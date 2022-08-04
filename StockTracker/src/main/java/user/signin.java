package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/signin")
public class signin {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	//Do user signup work here
	//http://localhost:8080/StockTracker/user/signin
	public Response userSignin(@QueryParam("email") String email, @QueryParam("password") String password) {


		Connection connection;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3508765";
			String dbusername = "sql3508765";
			String dbpassword = "DAwZbdY5HS";
			connection = DriverManager.getConnection(
					dbURL, dbusername, dbpassword);
			Statement statement = connection.createStatement();

			System.out.println("Request : "+email + " | "+password);

			String checkUser = "SELECT * FROM stockuser WHERE email = '"+email+"' AND password = '"+password+"'";			
			ResultSet rs = statement.executeQuery(checkUser);

			if(!rs.isBeforeFirst()) {			
				return Response.status(404).entity("Unable to verify user").build();		

			}else {
				System.out.println("User logged in succesfully");
				return Response.status(200).entity("User logged in succesfully").build();
			}


		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity("Unable to verify user").build();
		}


	}
}
