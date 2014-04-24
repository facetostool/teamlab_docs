package teamlabAPI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Request {
	
	private final String USER_AGENT = "Mozilla/5.0";
	private String url;
	private String authToken = null;
	private boolean useSSL = false;
	
	public void sendURL(String url) {
		this.url = url;
	}
	
	public void sendAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public void useSSL(boolean useSSL) {
		this.useSSL = useSSL;
	}
	
	private String fullURL() {
		String full;
		if (useSSL) {
			full = "https://" + this.url;
		} else {
			full = "http://" + this.url;
		}
		return full;
	}
	
	public StringBuffer sendGet() throws Exception {
		URL obj = new URL(fullURL());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
	
		return  readResponce(con);
	}
	
	public StringBuffer sendPost(HashMap params) throws IOException{
		String sendingParams = parseParams(params);
		URL obj = new URL(fullURL());
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		con.setDoInput(true); 
	
		//add request header
		con.setRequestMethod("POST");
		
		if (this.authToken != null) 
			con.setRequestProperty("authorization", this.authToken);
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(sendingParams);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + fullURL());
		System.out.println("Post parameters : " + sendingParams);
		System.out.println("Response Code : " + responseCode);
				
		return readResponce(con);		
	}
	
	@SuppressWarnings("deprecation<String, String>")
	private String parseParams(HashMap params) {
		String result = "";
	   	Iterator it = params.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        result += URLEncoder.encode((String) pairs.getKey()) + "=" + URLEncoder.encode((String) pairs.getValue());
	        if (it.hasNext()) {
	        	result +=  "&";
	        }
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		return result;
	}
	
	private StringBuffer readResponce(HttpURLConnection con) throws IOException {
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response;
	}

}
