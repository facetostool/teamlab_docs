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
        parseStringXML(myDocsXML);
        //System.out.println(XMLParser.parseStringXML(myDocsXML, "//title"));
	}

    public static String parseStringXML(String stringXML) {
        InputStream streamResponce = null;
        try {
            streamResponce = new ByteArrayInputStream(stringXML.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("\nCan't encoding in UTF-8 this xml: \n" + stringXML);
            e.printStackTrace();
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("\nCan't create new DocumentBuilder");
            e.printStackTrace();
        }
        Document document = null;
        try {
            if (db != null) {
                document = db.parse(streamResponce);
            }
        } catch (SAXException e) {
            System.out.println("\nSomething wrong with: \n" + streamResponce);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nSomething wrong with: \n" + streamResponce);
            e.printStackTrace();
        }

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = null;
        try {
            expr = xpath.compile("//files");
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        NodeList nl = null;
        try {
            nl = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        parseFiles(nl);
        String findingElem = "";
        return findingElem;
    }

    public static ArrayList<TeamlabDocumentFile> parseFiles(NodeList nodeFiles) {
        int totalFiles = nodeFiles.getLength();
        System.out.println("Total no of books : " + totalFiles);

        ArrayList<TeamlabDocumentFile> files = new ArrayList<TeamlabDocumentFile>();
        for(int i=0; i< totalFiles ; i++) {

            Node childNode = nodeFiles.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE) {

                XPathFactory xpathFactory = XPathFactory.newInstance();
                XPath xpath = xpathFactory.newXPath();
                // XPath Query for showing all nodes value
                try {
                    int id = Integer.parseInt(xpath.evaluate("id", childNode));
                    int folderId = Integer.parseInt(xpath.evaluate("folderId", childNode));
                    int version = Integer.parseInt(xpath.evaluate("version", childNode));
                    int versionGroup = Integer.parseInt(xpath.evaluate("versionGroup", childNode));
                    String contextLength = xpath.evaluate("contextLength", childNode);
                    int fileStatus = Integer.parseInt(xpath.evaluate("fileStatus", childNode));
                    String fileExst = xpath.evaluate("fileExst", childNode);
                    String title = xpath.evaluate("title", childNode);
                    int access = Integer.parseInt(xpath.evaluate("access", childNode));
                    boolean sharedByMe = Boolean.parseBoolean(xpath.evaluate("sharedByMe", childNode));
                    String updatedBy = xpath.evaluate("updatedBy", childNode);
                    String createdBy = xpath.evaluate("createdBy", childNode);
                    String updatedTime = xpath.evaluate("updatedTime", childNode);
                    TeamlabDocumentFile file = new TeamlabDocumentFile(id, folderId, version, versionGroup, contextLength, fileStatus, fileExst, title, access, sharedByMe, updatedBy, createdBy, updatedTime);
                    files.add(file);

                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }

            }
        }//end of for loop with s var

        return files;
    }

}
