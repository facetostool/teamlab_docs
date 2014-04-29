package teamlabAPI;

import java.util.HashMap;

public class TeamlabAPIAuth{

    private String apiURL = "";

    public TeamlabAPIAuth(String apiURL) {
        this.apiURL = apiURL + "/AUTHENTICATION";
    }

    public String getToken(String login, String password) {
        String token = "";
        HashMap<String,String> params =  new HashMap<String,String>();
        params.put("userName", login);
        params.put("password", password);
        String response = Request.sendRequest(Request.POST, this.apiURL, params, "");
        token = XMLParser.parseStringXML(response, "//token");
        return token;
    }

}
