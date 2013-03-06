package com.zillians.run_zillians_run.zs_flex_project;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zillians.run_zillians_run.zs_flex_project.preferences.PreferenceConstants;

//import org.eclipse.core.runtime.Plugin;

public class ZccRunner {
	static String[] PROP_LIST = { "GAMEID", "GAMENAME", "SOURCE", "OUTDIR" };

	public static boolean runZccCodeGeneratre(String filepath, String workFolder) {
		IPreferenceStore preStore = ZccActivator.getDefault().getPreferenceStore();
		HashMap<String, Object> hash_map = readZXML(filepath);
		
		String zcc_stub_gen_commands = "";
		zcc_stub_gen_commands += preStore.getString(PreferenceConstants.Z_ZCC_PATH );
		zcc_stub_gen_commands += " --gamename" + " " + hash_map.get("GAMENAME");
		zcc_stub_gen_commands += " --gameid" + " " + hash_map.get("GAMEID");
		zcc_stub_gen_commands += " --flex-stub" + " " + workFolder + "/"
				+ hash_map.get("OUTDIR") + "/";

		//String zillians_home = System.getenv("ZILLIANS_HOME");
		
		String str_zillians_lib_path = preStore.getString(PreferenceConstants.Z_LIB_PATH );
		
		if (str_zillians_lib_path == null) {
			System.err.println("###未找到环境变量 $ZILLIANS_HOME！");
			return false;
		}

		zcc_stub_gen_commands += " " + str_zillians_lib_path + "/DebugApi.zs";
		zcc_stub_gen_commands += " " + str_zillians_lib_path + "/EventApi.zs";
		zcc_stub_gen_commands += " " + str_zillians_lib_path + "/MathApi.zs";
		zcc_stub_gen_commands += " " + str_zillians_lib_path + "/SpaceApi.zs";

		{
			//String source_line = (String) (hash_map.get("SOURCE"));
			
			@SuppressWarnings("unchecked")
			List<String> sourceList = (List<String>) hash_map.get("SOURCE");
			
			for ( String s:sourceList ) {
				zcc_stub_gen_commands += " " + workFolder + "/" + s;
			}

		}

		try {
			System.out.println(" Compiling -- args " + zcc_stub_gen_commands);
			// test="/opt/zillians/bin/zcc2 --gamename ming --gameid 1 --flex-stub /home/merlyle/ /opt/zillians/lib/\\002Azs /home/merlyle/workspace/runtime-EclipseApplication/avatar/src/avatar/local_player.zs /home/merlyle/workspace/runtime-EclipseApplication/avatar/src/avatar/remote_player.zs";
			Runtime.getRuntime().exec(zcc_stub_gen_commands);
			
		} catch (IOException e) {
			System.out.println(" function execute error ");
		}
		
		return true;

	}

	public static HashMap<String, Object> readZXML(String filepath) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		DocumentBuilderFactory docFcatory	= DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder	docBuilder	= docFcatory.newDocumentBuilder();
			Document			doc			= docBuilder.parse(new FileInputStream(filepath));
			Element			root		= doc.getDocumentElement();
			
			map.clear();
			
			Node		n	= null;
			
			List<String> sourceList	= new ArrayList<String>();
			
			NodeList nodeList = root.getChildNodes();
			for (int i=0; i<nodeList.getLength(); i++) {
				n = nodeList.item(i);
				
				//添加单属性的节点
				if ( n.getNodeName().equals("GAMENAME")) {
					map.put("GAMENAME", n.getTextContent());
				}
				else if ( n.getNodeName().equals("GAMEID")) {
					map.put("GAMEID", n.getTextContent());
				}
				else if ( n.getNodeName().equals("OUTDIR")) {
					map.put("OUTDIR", n.getTextContent());
				}
				//多属性节点收集
				else if ( n.getNodeName().equals("SOURCE")) {
					NodeList sourceListNode = n.getChildNodes();
					for (int j=0; j<sourceListNode.getLength(); j++) {
						n = sourceListNode.item(j);
						if (n.getNodeName().equals("FILE")) {
							sourceList.add(sourceListNode.item(j).getTextContent());
						}
						
					}
					map.put("SOURCE", sourceList);
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
	
	public static void main (String[] args) {
		HashMap<String, Object> map = readZXML("/home/merlyle/workspace/runtime-EclipseApplication/avatar/src/avatar/testxml.zxml");
		System.out.println("###map：" + map);
	}
}