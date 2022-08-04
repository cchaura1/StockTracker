package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/news")
public class news {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	//Do user news  work here
	//http://localhost:8080/StockTracker/services/news
	public String fetchNewsData() {
		
		return "Do news data retrieval work here...";
	}

}
