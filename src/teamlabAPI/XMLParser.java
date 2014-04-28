package teamlabAPI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLParser {

	public static String parseStringXML(String stringXML, String xpathStr) {
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

        return parseElementXML(document.getDocumentElement(), xpathStr);
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
	
}
