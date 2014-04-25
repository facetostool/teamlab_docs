package teamlabAPI;

import java.util.HashMap;

public class TeamlabAPIFiles{

    private String token = "";
    private String apiURL = "";

    public TeamlabAPIFiles(String urlAPI, String token) {
        this.apiURL = urlAPI + "/files";
        this.token = token;
    }

    public String myDocuments() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendGet(this.apiURL + "/@my", params, this.token);
    }
}
