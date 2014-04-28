package test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import teamlabAPI.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
