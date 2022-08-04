package services;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

import org.json.CDL;
import org.json.JSONTokener;

import org.json.JSONArray;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/stock")
public class stock {


	@GET
	//Do user stock work here
	//http://localhost:8080/StockTracker/services/stock
	public Response getStockData(@QueryParam("ticker") String ticker){

		System.out.println(" Query>>> "+ticker);
		//String url = "https://finance.yahoo.com/quote/AAPL/history?p=AAPL";
		String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=1628019254&period2=1659555254&interval=1d&events=history&includeAdjustedClose=true", ticker);
		String USER_AGENT = "Mozilla/5.0";
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				int k=0;
				//This will hold the header
				//TODO ...
				String header ;
				while ((inputLine = in.readLine()) != null) {
					 if(k == 0){
						 	header = inputLine;
			                k++;
			                continue;
			            }

					 response.append(gson.toJson(inputLine));

				}
				in.close();

				//TODO : convert the response to JSON array
				return Response.status(200).entity(response.toString()).build();

			} else {
				System.out.println("GET request not worked");
				return Response.status(500).entity("Unable to find requested ticker").build();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity("Internal Server Error").build();
		}

	} 	



}
