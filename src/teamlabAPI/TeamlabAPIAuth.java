package teamlabAPI;

import java.util.HashMap;

public class TeamlabAPIAuth extends TeamlabAPI{

    public TeamlabAPIAuth(String portalName, String region, String server) {
        super(portalName, region, server);
        this.apiURL += "/AUTHENTICATION";
        this.request = new Request(this.apiURL, this.useSSL);
    }

    public String getToken(String login, String password) {
        String token = "";
        HashMap<String,String> params =  new HashMap<String,String>();
        params.put("userName", login);
        params.put("password", password);
        String response = request.sendPost(params);
        token = XMLParser.parseStringXML(response, "//token");
        return token;
    }

}
