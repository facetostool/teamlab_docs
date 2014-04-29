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
        return Request.sendRequest(Request.GET, this.apiURL + "/@my", params, this.token);
    }

    public String openFolder(String folderId) {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendRequest(Request.GET, this.apiURL + "/" + folderId, params, this.token);
    }

    public String trash() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendRequest(Request.GET, this.apiURL + "/@trash", params, this.token);
    }

    public String sharedWithMe() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendRequest(Request.GET, this.apiURL + "/@share", params, this.token);
    }

    public String commonDocuments() {
        HashMap<String,String> params =  new HashMap<String,String>();
        return Request.sendRequest(Request.GET, this.apiURL + "/@share", params, this.token);
    }

    public String createFolder(String parentId, String title) {
        HashMap<String,String> params =  new HashMap<String,String>();
        params.put("title", title);
        return Request.sendRequest(Request.POST, this.apiURL + "/" + parentId, params, this.token);
    }

    public String renameFolder(String parentId, String title) {
        HashMap<String,String> params =  new HashMap<String,String>();
        params.put("title", title);
        return Request.sendRequest(Request.PUT, this.apiURL + "/" + parentId, params, this.token);
    }

    public String renameFile(String fileId, String title) {
        HashMap<String,String> params =  new HashMap<String,String>();
        params.put("title", title);
        return Request.sendRequest(Request.PUT, this.apiURL + "/file/" + fileId, params, this.token);
    }

}
