package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class Example {
	public static void main(String[] args){
		String cuki=new String();
		try { 
			URL url = new URL("https://autotestdoc.teamlab.com/auth.aspx"); 
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoInput(true); 
			connection.setDoOutput(true);
			
			connection.setRequestMethod("POST"); 
			connection.setFollowRedirects(true); 
			
			
			String query = "login=" + URLEncoder.encode("shockwavenn@gmail.com"); 
			query += "&"; 
			query += "password=" + URLEncoder.encode("123456"); 
			query += "&"; 
			query += "__VIEWSTATE=" + URLEncoder.encode("/wEPDwUJNjkxNjkyODk0ZGQ="); 									
			
			connection.setRequestProperty("Content-length",String.valueOf (query.length())); 
			connection.setRequestProperty("Content-Type","application/x-www- form-urlencoded"); 
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36"); 
			
			
			
			// open up the output stream of the connection 
			DataOutputStream output = new DataOutputStream( connection.getOutputStream() ); 
			
			
			// write out the data 
			int queryLength = query.length(); 
			output.writeBytes( query ); 
			//output.close();
			
			
			System.out.println("Resp Code:"+connection.getResponseCode()); 
			System.out.println("Resp Message:"+ connection.getResponseMessage()); 
			
			
			// get ready to read the response from the cgi script 
			DataInputStream input = new DataInputStream( connection.getInputStream() ); 
			
			
			// read in each character until end-of-stream is detected 
			for( int c = input.read(); c != -1; c = input.read() ) 
			    System.out.print( (char)c ); 
			input.close(); 
		} 
		catch(Exception e) 
		{ 
			System.out.println( "Something bad just happened." ); 
			System.out.println( e ); 
			e.printStackTrace(); 
		} 
	}
}
