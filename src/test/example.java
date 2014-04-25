package test;

import teamlabAPI.*;

public class example {

	public static void main(String args[]) {
        TeamlabAPI teamlab = new TeamlabAPI("autotestdoc", "us", "com");
        teamlab.auth("shockwavenn@gmail.com", "123456");
        TeamlabAPIFiles filesAPI = teamlab.documentsAPI();
        System.out.println(filesAPI.myDocuments());
	}

}
