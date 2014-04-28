package teamlabAPI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

    public static Document getDocument(String xml){
        InputStream streamResponce = null;
        try {
            streamResponce = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("\nCan't encoding in UTF-8 this xml: \n" + xml);
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

        return document;
    }

	public static String parseStringXML(String stringXML, String xpathStr) {
        Document doc = getDocument(stringXML);

        return parseElementXML(doc.getDocumentElement(), xpathStr);
	}

    public static String parseElementXML(Element element, String xpathStr) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        String findingElem = null;
        try {
            findingElem = xpath.evaluate(xpathStr, element);
        } catch (XPathExpressionException e) {
            System.out.println("\nCan't find '" + xpathStr + "' in: \n" + element);
            e.printStackTrace();
        }
        return findingElem;
    }

    public static ArrayList<TeamlabDocumentFile> getFiles(Document document) {
        NodeList filesNodeList = getNodesByXPaths(document, "//files");
        return parseFiles(filesNodeList);
    }

    public static ArrayList<TeamlabDocumentFolder> getFolders(Document document) {
        NodeList foldersNodeList = getNodesByXPaths(document, "//folders");
        return parseFolders(foldersNodeList);
    }

    public static NodeList getNodesByXPaths(String stringXML, String xpathStr) {

        Document doc = getDocument(stringXML);

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = null;
        try {
            expr = xpath.compile(xpathStr);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return nodeList;
    }

    public static NodeList getNodesByXPaths(Document doc, String xpathStr) {

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = null;
        try {
            expr = xpath.compile(xpathStr);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return nodeList;
    }

    public static ArrayList<TeamlabDocumentFile> parseFiles(NodeList nodeFiles) {
        int totalFiles = nodeFiles.getLength();
        System.out.println("Total of files : " + totalFiles);

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
                    String updatedBy = xpath.evaluate("updatedBy/displayName", childNode);
                    String createdBy = xpath.evaluate("createdBy/displayName", childNode);
                    String updatedTime = xpath.evaluate("updatedTime", childNode);
                    TeamlabDocumentFile file = new TeamlabDocumentFile(id, folderId, version, versionGroup,
                            contextLength, fileStatus, fileExst, title, access, sharedByMe, updatedBy, createdBy,
                            updatedTime);
                    files.add(file);

                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }

            }
        }//end of for loop with s var

        return files;
    }

    public static ArrayList<TeamlabDocumentFolder> parseFolders(NodeList nodeFolders) {
        int totalFolders = nodeFolders.getLength();
        System.out.println("Total of files : " + totalFolders);

        ArrayList<TeamlabDocumentFolder> folders = new ArrayList<TeamlabDocumentFolder>();
        for(int i=0; i< totalFolders ; i++) {

            Node childNode = nodeFolders.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE) {

                XPathFactory xpathFactory = XPathFactory.newInstance();
                XPath xpath = xpathFactory.newXPath();
                // XPath Query for showing all nodes value
                try {
                    String id = xpath.evaluate("id", childNode);
                    int parentId = Integer.parseInt(xpath.evaluate("parentId", childNode));
                    int filesCount = Integer.parseInt(xpath.evaluate("filesCount", childNode));
                    int foldersCount = Integer.parseInt(xpath.evaluate("foldersCount", childNode));
                    boolean isShareable = Boolean.parseBoolean(xpath.evaluate("isShareable", childNode));
                    String title = xpath.evaluate("title", childNode);
                    int access = Integer.parseInt(xpath.evaluate("access", childNode));
                    boolean sharedByMe = Boolean.parseBoolean(xpath.evaluate("sharedByMe", childNode));
                    String updatedBy = xpath.evaluate("updatedBy/displayName", childNode);
                    String createdBy = xpath.evaluate("createdBy/displayName", childNode);
                    String updatedTime = xpath.evaluate("updatedTime", childNode);
                    TeamlabDocumentFolder folder = new TeamlabDocumentFolder(id, parentId, filesCount, foldersCount,
                            isShareable, title, access, sharedByMe, updatedBy, createdBy, updatedTime);
                    folders.add(folder);

                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }

            }
        }//end of for loop with s var

        return folders;
    }
}
