package teamlabAPI;

/**
 * Created by runner on 4/25/14.
 */
public class TeamlabURICreator {
    private static final String EU_REGION = "eu";
    private static final String US_REGION = "us";
    private static final String INFO_SERVER = "info";
    private static final String COM_SERVER = "com";
    private static final String SERVICE_NAME = "teamlab";
    private static final String API_VERSION = "/API/2.0";

    public static String createUrl(String portalName, String region, String server) {
        String url = "";
        if (server.equals(INFO_SERVER)) {
            if(region.equals(EU_REGION)) {
                url = portalName + "."  + SERVICE_NAME + "." + EU_REGION + "." +
                        INFO_SERVER + API_VERSION;
            } else if(region.equals(US_REGION)){
                url = portalName + "."  + SERVICE_NAME + "." + INFO_SERVER + API_VERSION;
            } else {
                throw new IllegalArgumentException("Wrong region: " + region + "\nUse 'eu' or 'us'!");
            }
        } else if (server.equals(COM_SERVER)){
            if(region.equals(EU_REGION)) {
                url = portalName + "."  + SERVICE_NAME + "." + EU_REGION + "."
                        + COM_SERVER + API_VERSION;
            } else if(region.equals(US_REGION)){
                url = portalName + "."  + SERVICE_NAME + "." + COM_SERVER + API_VERSION;
            } else {
                throw new IllegalArgumentException("Wrong region: " + region + "\nUse 'eu' or 'us'!");
            }
        } else {
            throw new IllegalArgumentException("Wrong server: " + server + "\nUse 'com' or 'info'!");
        }
        return url;
    }
}
