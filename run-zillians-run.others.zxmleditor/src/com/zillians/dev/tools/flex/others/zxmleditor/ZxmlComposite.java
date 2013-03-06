package com.zillians.dev.tools.flex.others.zxmleditor;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class ZxmlComposite extends Composite{
	private Text gameNameText = null;
	private Text gameIdText = null;
	private Text outDirText = null;
	private Text sourceText = null;
	private Button	applyButton = null;
	
	private IDocument	idoc = null;

	public ZxmlComposite(Composite parent, int style, IDocument idoc) {
		super(parent, style);
		this.idoc = idoc;
		
		initLayout();
		
		applyButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveIdoc();
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});

	}
	


	public void reload() {
		String docContent = idoc.get();
		System.out.println("###doc:" + docContent);
		if (docContent == null || docContent.trim().equals("")) {
			return;
		}
		Reader reader = new StringReader(docContent);
		
		DocumentBuilderFactory docFcatory	= DocumentBuilderFactory.newInstance();
		DocumentBuilder	docBuilder = null;
		Document			doc = null;
		try {
			docBuilder = docFcatory.newDocumentBuilder();
			doc = docBuilder.parse(new InputSource(reader));
			Element			root		= doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			Node n = null;
			for (int i=0; i<nodeList.getLength(); i++) {
				n = nodeList.item(i);
				
				//添加单属性的节点
				if ( n.getNodeName().equals("GAMENAME")) {
					gameNameText.setText(n.getTextContent().trim());
				}
				else if ( n.getNodeName().equals("GAMEID")) {
					gameIdText.setText(n.getTextContent().trim());
				}
				else if ( n.getNodeName().equals("OUTDIR")) {
					outDirText.setText(n.getTextContent().trim());
				}
				//多属性节点收集
				else if ( n.getNodeName().equals("SOURCE")) {
					NodeList sourceListNode = n.getChildNodes();
					String sourceStr = "";
					for (int j=0; j<sourceListNode.getLength(); j++) {
						n = sourceListNode.item(j);
						if (n.getNodeName().equals("FILE")) {
							sourceStr += n.getTextContent().trim() + ", ";
						}
					}
					sourceText.setText(sourceStr.substring(0, sourceStr.length()-2));
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void initLayout() {
		GridLayout layout = new GridLayout();
		this.setLayout(layout);
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		
		Label gameNameLabel = new Label(this, SWT.PUSH);
		GridData gnl_gd = new GridData(GridData.END);
		gameNameLabel.setLayoutData(gnl_gd);
		gameNameLabel.setText("Game Name:");
		
		gameNameText = new Text(this, SWT.PUSH);
		GridData gnt_gd = new GridData(GridData.GRAB_HORIZONTAL);
		gnt_gd.widthHint = 200;
		gameNameText.setLayoutData(gnt_gd);
		gameNameText.setEditable(true);
		
		Label gameIdLabel = new Label(this, SWT.PUSH);
		GridData gil_gd = new GridData(GridData.END);
		gameIdLabel.setLayoutData(gil_gd);
		gameIdLabel.setText("Game ID:");
		
		gameIdText = new Text(this, SWT.PUSH);
		GridData git_gd = new GridData(GridData.GRAB_HORIZONTAL);
		git_gd.widthHint = 200;
		gameIdText.setLayoutData(git_gd);
		gameIdText.setEditable(true);
		
		Label outDirLabel = new Label(this, SWT.PUSH);
		GridData odl_gd = new GridData(GridData.END);
		outDirLabel.setLayoutData(odl_gd);
		outDirLabel.setText("Output Dir:");
		
		outDirText = new Text(this, SWT.PUSH);
		GridData odt_gd = new GridData(GridData.GRAB_HORIZONTAL);
		odt_gd.widthHint = 200;
		outDirText.setLayoutData(odt_gd);
		outDirText.setEditable(true);
		
		Label sourceLabel = new Label(this, SWT.PUSH);
		GridData sl_gd = new GridData(GridData.END);
		sourceLabel.setLayoutData(sl_gd);
		sourceLabel.setText("Source list (beta):");
		
		sourceText = new Text(this, SWT.PUSH);
		GridData st_gd = new GridData(GridData.GRAB_HORIZONTAL);
		st_gd.widthHint = 400;
		sourceText.setLayoutData(st_gd);
		sourceText.setEditable(true);
		
		applyButton = new Button(this, SWT.PUSH);
		GridData ab_gd = new GridData(GridData.END);
		sourceLabel.setLayoutData(ab_gd);
		applyButton.setText("apply");
		
		
		
		Button applyButton2 = new Button(this, SWT.PUSH);
		applyButton2.setText("测试");
		
		pack();
	}
	
	private void saveIdoc() {
		StringBuffer sb = new StringBuffer();
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" );
		sb.append( "<ZILLIANS xmlns=\"http://www.w3school.com.cn\"\n" );
		sb.append( "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" );
		sb.append( "xsi:schemaLocation=\"zillians-flex.xsd\">\n" );
		
		sb.append( "\t<!--Test-->\n" );
		sb.append( "\t<GAMENAME>" + gameNameText.getText().trim() + "</GAMENAME>\n" );
		sb.append( "\t<GAMEID>" + gameIdText.getText().trim() + "</GAMEID>\n" );
		sb.append( "\t<SOURCE>\n" );
		
		String str = sourceText.getText().trim();
		String strFiles[] = str.split(",");
		for (String s:strFiles) {
			sb.append( "\t\t<FILE>" + s.trim() + "</FILE>\n" );
		}
		sb.append( "\t</SOURCE>\n" );
				sb.append( "\t<OUTDIR>" + outDirText.getText().trim() + "</OUTDIR>\n" );
				sb.append( "</ZILLIANS>\n" );
				idoc.set( sb.toString() );
	}
	


}
