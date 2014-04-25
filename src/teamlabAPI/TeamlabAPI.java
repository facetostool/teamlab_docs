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
        TeamlabAPIAuth auth = new TeamlabAPIAuth(this.portalName, this.region, this.server);
        this.token = auth.getToken(userName, password);
    }
	
}

//nct                   com/eu.com/etc            
//http://<portalName>.teamlab.<region>/API/2.0/