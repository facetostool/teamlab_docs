package test;

import org.w3c.dom.Document;
import teamlabAPI.*;

import java.util.ArrayList;

public class example {

	public static void main(String args[]) {
        TeamlabAPI teamlab = new TeamlabAPI("autotestdoc", "us", "com");
        teamlab.auth("shockwavenn@gmail.com", "123456");
        TeamlabAPIFiles filesAPI = teamlab.documentsAPI();
        String myDocsXML = filesAPI.myDocuments();
        Document document = XMLParser.getDocument(myDocsXML);
        ArrayList<TeamlabDocumentFile> files = XMLParser.getFiles(document);
        ArrayList<TeamlabDocumentFolder> folders = XMLParser.getFolders(document);
        String folderXML = filesAPI.openFolder("1553123");
        System.out.println(folderXML);
	}

}
