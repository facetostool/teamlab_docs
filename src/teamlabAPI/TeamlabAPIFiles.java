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

    public String openFolder(String folderId) {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendGet(this.apiURL + "/" + folderId, params, this.token);
    }

    public String trash() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendGet(this.apiURL + "/@trash", params, this.token);
    }

    public String sharedWithMe() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendGet(this.apiURL + "/@share", params, this.token);
    }

    public String commonDocuments() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendGet(this.apiURL + "/@share", params, this.token);
    }

    public String createFolder(String parentId, String title) {
        HashMap<String,String> params =  new HashMap<String,String>();
        params.put("title", title);
        return Request.sendPost(this.apiURL + "/" + parentId, params, this.token);
    }

}
