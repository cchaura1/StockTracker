package user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/watchlist")
public class watchlist {

	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	//Do user watchlist work here
	//http://localhost:8080/StockTracker/user/watchlist
	public String userWatchlist() {
		
		return "return user watchlist here";
	}
}
