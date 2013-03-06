package com.zillians.dev.tools.flex.platform.core.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UploadDialogXmlReader {
	
	private ArrayList<String> projectList = new ArrayList<String>();
	
	private int	message = -1;
	
	public boolean analysisXml ( InputStream ins ) {
		
		DocumentBuilderFactory docFcatory	= DocumentBuilderFactory.newInstance();
		DocumentBuilder	docBuilder = null;
		Document			doc = null;
		
		try {
			docBuilder = docFcatory.newDocumentBuilder();
			doc = docBuilder.parse(new InputSource( ins ));
			Element			root		= doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			Node n = null;
			for (int i=0; i<nodeList.getLength(); i++) {
				n = nodeList.item(i);
				
				if ( n.getNodeName().equals("message")) {
					String tmp = n.getTextContent().trim();
					System.out.println("===get message:" + tmp);
					message = Integer.valueOf( tmp );
				}
				
				if ( n.getNodeName().equals("project_list")) {
					NodeList projectListNode = n.getChildNodes();
					
					for (int j=0; j<projectListNode.getLength(); j++) {
						n = projectListNode.item(j);
						if (n.getNodeName().equals("project")) {
							projectList.add(n.getTextContent().trim());
						}
					}
				}
				
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (message == -1) { 
			return false;
		}
		
		return true;
	}

	public ArrayList<String> getProjectList() {
		return projectList;
	}

	public int getMessage() {
		return message;
	}

}
