package teamlabAPI;

public class TeamlabAPI {
	
	private String portalName = "";
	private String region = "";
    private String server = "";

    protected String apiURL = "";
    protected final boolean useSSL = true;
    protected Request request;
    protected String token = "";

    public TeamlabAPI(String portalName, String region, String server) {
        this.portalName = portalName;
        this.region = region;
        this.server = server;
        this.apiURL = TeamlabURICreator.createUrl(portalName, region, server);
    }

    public void auth(String userName, String password) {
        TeamlabAPIAuth auth = new TeamlabAPIAuth(this.apiURL);
        String token = auth.getToken(userName, password);
        System.out.println("Your token: " + token);
        if (!token.equals("")) {
            this.token = token;
        } else {
            throw new IllegalArgumentException("Empty token!");
        }
    }

    public TeamlabAPIFiles documentsAPI() {
        TeamlabAPIFiles documentsAPI = null;
        if(this.token != null) {
            documentsAPI = new TeamlabAPIFiles(this.apiURL, this.token);
        } else {
            throw new IllegalAccessError("Please log in!");
        }
        return documentsAPI;
    }
	
}

//nct                   com/eu.com/etc            
//http://<portalName>.teamlab.<region>/API/2.0/