package teamlabAPI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Request {

    public static String POST = "POST";
    public static String GET = "GET";
    public static String PUT = "PUT";
    public static String DELETE = "DELETE";

	private static boolean USE_SSL = true;
	
	private static String fullURL(String url) {
		String full;
		if (USE_SSL) {
			full = "https://" + url;
		} else {
			full = "http://" + url;
		}
		return full;
	}

    public static HttpsURLConnection createConnection(String url) {
        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("Can't parse string to url: \n" + url);
            e.printStackTrace();
        }
        HttpsURLConnection con = null;
        if (obj != null) {
            try {
                con = (HttpsURLConnection) obj.openConnection();
            } catch (IOException e) {
                System.out.println("Can't open connection! In url: " + url);
                e.printStackTrace();
            }
        }
        return con;
    }

    public static String sendRequest(String requestType, String url, HashMap params, String token) {
        String response = null;
        String sendingParams = parseParams(params);
        String fullUrl = fullURL(url);
        HttpsURLConnection con = createConnection(fullUrl);

        if (con != null) {
            con.setRequestProperty("Authorization", token);

            try {
                con.setRequestMethod(requestType);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            // Send post request
            con.setDoOutput(true);

            if(!sendingParams.equals("")) {
                DataOutputStream wr = null;
                try {
                    wr = new DataOutputStream(con.getOutputStream());
                } catch (IOException e) {
                    System.out.println("Can't read DataOutputStream!");
                    e.printStackTrace();
                }
                if (wr != null) {
                    try {
                        wr.writeBytes(sendingParams);
                        wr.flush();
                        wr.close();
                    } catch (IOException e) {
                        System.out.println("Can't puts params for request: " + sendingParams);
                        e.printStackTrace();
                    }
                }
            }

            int responseCode = 0;
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nSending 'GET' request to URL : " + fullUrl);
            System.out.println("Post parameters : " + sendingParams);
            System.out.println("Response Code : " + responseCode);
            try {
                response = readResponce(con).toString();
            } catch (IOException e) {
                System.out.println("Can't read response.");
                e.printStackTrace();
            }
        }

        return  response;
    }
	
	@SuppressWarnings("deprecation<String, String>")
	private static String parseParams(HashMap params) {
		String result = "";
        if (params != null || !params.isEmpty()) {
            Iterator it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                result += URLEncoder.encode((String) pairs.getKey()) + "=" + URLEncoder.encode((String) pairs.getValue());
                if (it.hasNext()) {
                    result +=  "&";
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
		return result;
	}
	
	private static StringBuffer readResponce(HttpURLConnection con) throws IOException {
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
