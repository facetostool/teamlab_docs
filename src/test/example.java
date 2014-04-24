package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import teamlabAPI.*;

public class example {
	public static void main(String args[]) { 
		
		Request request = new Request(); 
		request.sendURL("autotestdoc.teamlab.com/api/2.0/authentication");
		request.useSSL(true);
		
		HashMap<String,String> params =  new HashMap<String,String>();
		params.put("userName", "shockwavenn@gmail.com");
		params.put("password", "123456");
		try {
			StringBuffer response = request.sendPost(params);
			System.out.println(response.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
