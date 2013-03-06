package com.zillians.run_zillians_run.zs_flex_project.actions.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.zillians.run_zillians_run.zs_flex_project.ZccActivator;

public class UploadZipTool {
	
	public static String outputDir = "/tmp/";
	public static String outputFileName = "zsTemp.zar";

	private String projectLocation = "";
	private List<String> sourcesList = new ArrayList<String>();
	
	private static UploadZipTool _ZipTool = null;
	
	private UploadZipTool() {
	}
	
	public static UploadZipTool getInit() {
		if ( _ZipTool == null ) {
			_ZipTool = new UploadZipTool();
		}
		return _ZipTool;
	}
	
	private boolean readZxml () {
		if (projectLocation.equals("")) {
			System.out.println("###项目路径为空！");
			return false;
		}
		
		String zxmlSource = "/src/zillians.zxml";
		
		File zxmlFile = new File(projectLocation + zxmlSource ); 
		
		if ( !zxmlFile.exists()) {
			System.out.println("###zillians.zxml不存在");
			return false;
		}
		DocumentBuilderFactory docFcatory	= DocumentBuilderFactory.newInstance();
		DocumentBuilder	docBuilder = null;
		Document			doc = null;
		try {
			docBuilder = docFcatory.newDocumentBuilder();
			doc = docBuilder.parse(new InputSource(new FileReader(zxmlFile)));
			Element			root		= doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			Node n = null;
			for (int i=0; i<nodeList.getLength(); i++) {
				n = nodeList.item(i);
				if ( n.getNodeName().equals("SOURCE")) {
					NodeList sourceListNode = n.getChildNodes();
					sourcesList.clear();
					for (int j=0; j<sourceListNode.getLength(); j++) {
						n = sourceListNode.item(j);
						if (n.getNodeName().equals("FILE")) {
							sourcesList.add(n.getTextContent().trim());
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
		
		return true;
	}

	
	public boolean compress () {
		if ( ! readZxml()) {
			System.out.println("###读取zxml文件失败。");
			return false;
		}
		try {
			CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(outputDir + outputFileName), new CRC32());
			ZipOutputStream zos = new ZipOutputStream(cos); 
			
			for (String fileUrl: sourcesList) {
				
				ZipEntry entry = new ZipEntry(fileUrl);
				zos.putNextEntry(entry);
					
				fileUrl = projectLocation + fileUrl;
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(  
						fileUrl));  
				  
				int count;  
				byte data[] = new byte[1024];  
				while ((count = bis.read(data, 0, 1024)) != -1) {  
				    zos.write(data, 0, count); 
				}  
				bis.close();
			}
			
			String zxmlUrl = "zillians.zxml";
			
			ZipEntry entry = new ZipEntry(zxmlUrl);
			zos.putNextEntry(entry);
			
			zxmlUrl = projectLocation + "/src/zillians.zxml";
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(  
					zxmlUrl));  
			  
			int count;  
			byte data[] = new byte[1024];  
			while ((count = bis.read(data, 0, 1024)) != -1) {  
			    zos.write(data, 0, count); 
			}  
			bis.close();
			
			
			zos.close();
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		
		
		return true;
	}
	
	
	
	public List<String> getSourcesList() {
		return sourcesList;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	
	
}
