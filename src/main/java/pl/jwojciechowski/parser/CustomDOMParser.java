package pl.jwojciechowski.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class CustomDOMParser {
	DocumentBuilderFactory dbFactory = null;
	private DocumentBuilder dBuilder = null;
	private Document doc = null;
	private ErrorHandler errorHandler = null;
	
	public void setErrorHandler(ErrorHandler er) {
		this.errorHandler = er;
	}

	public boolean parseFile(File xmlFile) {
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			dBuilder.setErrorHandler(errorHandler);
			doc = dBuilder.parse(xmlFile);
			return true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	public List<String> getAttributes(String elementName, String attributeName) {
		NodeList list = doc.getElementsByTagName(elementName);
		List<String> alist = new ArrayList<String>();
		for (int i = 0; i < list.getLength(); i++) {
			Node nNode = list.item(i);
			Element eElement = (Element) nNode;
			alist.add(eElement.getAttribute(attributeName));
		}
		return alist;
	}
	*/

	public List<String> getValues(String elementName) {
		NodeList list = doc.getElementsByTagName(elementName);
		List<String> alist = new ArrayList<String>();
		for (int i = 0; i < list.getLength(); i++) {
			alist.add(list.item(i).getTextContent());
		}
		return alist;
	}

}