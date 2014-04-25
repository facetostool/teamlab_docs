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
	
	private final String USER_AGENT = "Mozilla/5.0";
	private String url;
	private boolean useSSL = false;

    public Request() {

    }

    public Request(String url, Boolean useSSL) {
        this.url = url;
        this.useSSL = useSSL;
    }
	
	public void sendURL(String url) {
		this.url = url;
	}
	
	public void useSSL(boolean useSSL) {
		this.useSSL = useSSL;
	}
	
	private String fullURL() {
		String full;
		if (this.useSSL) {
			full = "https://" + this.url;
		} else {
			full = "http://" + this.url;
		}
		return full;
	}

    public HttpsURLConnection createConnection(String url) {
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
	
	public String sendGet(HashMap params) {
        String response = null;
        String sendingParams = parseParams(params);
        String fullUrl = fullURL();
        HttpsURLConnection con = createConnection(fullUrl);

        if (con != null) {
            try {
                con.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            // Send post request
            con.setDoOutput(true);

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

            int responseCode = 0;
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nSending 'GET' request to URL : " + fullUrl);
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
	
	public String sendPost(HashMap params) {
        String fullUrl = fullURL();
		String sendingParams = parseParams(params);
        String response = null;
        HttpsURLConnection con = createConnection(fullUrl);

        if (con != null) {
            con.setDoInput(true);
            //add request header
            try {
                con.setRequestMethod("POST");
            } catch (ProtocolException e) {
                System.out.println("Can't send POST request for url: " + fullUrl);
                e.printStackTrace();
            }

            // Send post request
            con.setDoOutput(true);

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

            int responseCode = 0;
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                System.out.println("Can't getting response code.");
                e.printStackTrace();
            }
            System.out.println("\nSending 'POST' request to URL : " + fullURL());
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
	private String parseParams(HashMap params) {
		String result = "";
        if (params != null) {
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
