package com.zillians.dev.tools.flex.platform.core.upload;


import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zillians.dev.tools.flex.platform.core.ZilliansActivator;
import com.zillians.dev.tools.flex.platform.core.preferences.PreferenceConstants;


public class UploadDialog extends Dialog {
	
	private	Label	usernameLabel = null;
	private	Label	passwordLabel = null;
	private	Text	usernameText	= null;
	private	Text	passwordText	= null;
	private	Button	getProjectsButton		= null;
	private	Label	getProjectsInfoLabel = null;
	
	private	Label	projectsListLabel	= null;
	private	Combo	projectsListCombo	= null;
	private	Label	commitInfoLabel	= null;
	private	Text	commitInfoText	= null;
	private	Label	projectInfoLael	= null;
	
	
	private	Button		okBtn = null;
	private	Button		cancelBtn = null;
	
	private	String		serverUrl = "";
	

	public UploadDialog(Shell parentShell) {
		super(parentShell);

	}
	
	
	
	protected Control createDialogArea(Composite parent) { 
		
		
		Composite composite = (Composite)super.createDialogArea(parent);
		composite.setLayout(null);
		Group userGroup = new Group( composite, SWT.SHADOW_OUT); 
		userGroup.setText("用户信息"); 
		userGroup.setLocation(20, 10);
		userGroup.setSize(600, 160);
		Group projectGroup = new Group(composite, SWT.SHADOW_OUT); 
		projectGroup.setText("项目信息");
		projectGroup.setLocation(20, 180);
		projectGroup.setSize(600, 210);
		
		usernameLabel = new Label(userGroup, SWT.RIGHT);
		passwordLabel = new Label(userGroup, SWT.RIGHT);
		usernameText	= new Text(userGroup, SWT.NONE);
		passwordText	= new Text(userGroup, SWT.NONE);
		getProjectsButton = new Button(userGroup, SWT.NONE);
		getProjectsInfoLabel = new Label(userGroup, SWT.BORDER);
		
		usernameLabel.setText("用户名：");
		passwordLabel.setText("密码：");
		
		usernameText.setText("a");
		passwordText.setText("a");
		
		getProjectsButton.setText("获取项目档案");
		getProjectsInfoLabel.setText("与服务器 通信中……");
		//getProjectsInfoLabel.setText("请点击\"获取项目档案\"按钮……");
		
		passwordText.setEchoChar('*');
		
		int		labelLeft	= 10;
		Point	labelSize	= new Point(72, 24);
		int		textLeft	= 90;
		Point	textSize	= new Point(150, 24);
		int		infoLeft	= 300;
		Point	buttonSize	= new Point(120, 28);
		
		usernameLabel.setLocation(labelLeft, 40);
		usernameLabel.setSize(labelSize);
		passwordLabel.setLocation(labelLeft, 80);
		passwordLabel.setSize(labelSize);
		usernameText.setLocation(textLeft, 40);
		usernameText.setSize(textSize);
		passwordText.setLocation(textLeft, 80);
		passwordText.setSize(textSize);
		getProjectsInfoLabel.setLocation(infoLeft, 40);
		getProjectsInfoLabel.setSize(260, 100);
		getProjectsButton.setLocation(textLeft, 120);
		getProjectsButton.setSize(buttonSize);
		
		getProjectsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				getProjectsButtonPress();
			}
		});
		
		projectsListLabel	= new Label(projectGroup, SWT.NONE);
		projectsListCombo	= new Combo(projectGroup, SWT.NONE);
		commitInfoLabel	= new Label(projectGroup, SWT.NONE);
		commitInfoText	= new Text(projectGroup, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		projectInfoLael	= new Label(projectGroup, SWT.BORDER);
		
		projectsListLabel.setText("请选择要上传的项目");
		commitInfoLabel.setText("填写项目版本更新信息：");
		projectInfoLael.setText("选中项目，显示详细信息……");
		
		
		projectsListLabel.setLocation(labelLeft, 30);
		projectsListCombo.setLocation(labelLeft, 60);
		commitInfoLabel.setLocation(labelLeft, 100);
		commitInfoText.setLocation(labelLeft, 130);
		projectInfoLael.setLocation(infoLeft, 40);
		
		projectsListLabel.setSize(200, 24);
		projectsListCombo.setSize(200, 24);
		commitInfoLabel.setSize(200, 24);
		commitInfoText.setSize(260, 60);
		projectInfoLael.setSize(260, 150);
		
		
		projectsListCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				projectsListComboSelection();
			}
		});
		
		
		this.getShell().setText("zillians code 上传对话框");
		
		return composite;
	}

	private void getProjectsButtonPress() {
		projectsListCombo.removeAll();
		connLogin();
	}
	
	private void projectsListComboSelection() {
		connOpenProject();
	}
	
	private void UploadButtonPress() {
		connUpload();
	}
	
	/** 覆盖父类方法实现自定义 dialog 大小 */
	@Override 
	protected Point getInitialSize() { 
		return new Point(640,480); 
	}


	/** 覆盖父类方法实现自定义 dialog 按钮 */
	@Override
	protected void initializeBounds() {
		super.initializeBounds();
		okBtn		= getButton(IDialogConstants.OK_ID);
		cancelBtn	= getButton(IDialogConstants.CANCEL_ID);
		
		okBtn.setText("上传");
		cancelBtn.setText("取消");
		
		okBtn.setEnabled(false);
		
		okBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UploadButtonPress();
			}
		});
		
		connGetCookie();
		
	} 
	
	private void connGetCookie() {
		
		IPreferenceStore preStore = ZilliansActivator.getDefault().getPreferenceStore();
		serverUrl = preStore.getString(PreferenceConstants.Z_UPLOAD_URL);
		
		System.out.println("===serverUrl:" + serverUrl);
		
		UploadDialogXmlReader reader = null;
		try {
			reader = UploadURLConnection.getInit().firstConnection(
					new URL( serverUrl + "eclipse_plugin/get_cookie/")) ;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if ( reader == null ) {
			getProjectsInfoLabel.setText("连接服务器失败！\n\n请检查您的网络连接……");
			getProjectsButton.setEnabled(false);
			return;
			//TODO 返回 reader 统一处理，
			
		}
		switch ( reader.getMessage() ) {
			case ServerMessages.Server_Valid:
				getProjectsInfoLabel.setText("连接服务器成功！\n\n请填写帐号密码，获取项目列表……");
				break;
			case ServerMessages.Server_Invalid:
				getProjectsInfoLabel.setText("连接服务器成功！\n\n但目前无法登录，请联系系统管理员……");
				getProjectsButton.setEnabled(false);
				break;
			default:
				break;
		}
		

	}
	
	private void connLogin() {
		UploadDialogXmlReader reader = null;
		try {
			reader = UploadURLConnection.getInit().loginConnection (
							new URL( serverUrl + "eclipse_plugin/login/"),
							usernameText.getText().trim(),
							passwordText.getText().trim()
							);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
			
		if (reader==null ) {
			getProjectsInfoLabel.setText("解析服务器返回信息失败，请联系系统管理员。");
			return;			
		}
		
		switch ( reader.getMessage() ) {
			case ServerMessages.Login_Success:
				getProjectsInfoLabel.setText("登录成功！");
				connGetProjects();
				break;
			case ServerMessages.AccoutOrPasswordError:
				getProjectsInfoLabel.setText("帐号或密码错误，请重试！");
				break;
			default:
				break;
		}
	}
	
	private void connGetProjects(){
		UploadDialogXmlReader reader = null;
		try {
			reader = UploadURLConnection.getInit().getProjectsConnection (
					new URL( serverUrl + "eclipse_plugin/get_projects/"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if ( reader == null ) {
			getProjectsInfoLabel.setText("解析服务器返回信息失败，请联系系统管理员。");
			return;
		}
		
		switch ( reader.getMessage() ) {
			case ServerMessages.GetProjectsSuccess:
				getProjectsInfoLabel.setText("获取项目列表成功！");
				for ( String s: reader.getProjectList() ) {
					projectsListCombo.add(s);
				}
				projectsListCombo.select(0);
				connOpenProject();
				break;
			case ServerMessages.NoProjects:
				break;
			default:
				break;
		}

	}
	
	private void connOpenProject() {
		UploadDialogXmlReader reader = null;
		try {
			reader = UploadURLConnection.getInit().openProjectConnection(
					new URL( serverUrl + "eclipse_plugin/p/" 
							+ projectsListCombo.getText() + "/")) ;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if ( reader == null ) {
			getProjectsInfoLabel.setText("连接服务器失败！请检查您的网络连接……");
			getProjectsButton.setEnabled(false);
			return;
			//TODO 返回 reader 统一处理，
			
		}
		switch ( reader.getMessage() ) {
			case ServerMessages.ProjectValid:
				projectInfoLael.setText("读取项目：" + projectsListCombo.getText() + "成功！将显示日志……");
				okBtn.setEnabled(true);
				break;
			case ServerMessages.ProjectInvalid:
				projectInfoLael.setText("读取项目出现异常，请重试……");
				break;
			default:
				break;
		}
	}
	
	private void connUpload () {
		UploadDialogXmlReader reader = null;
		try {
			reader = UploadURLConnection.getInit().uploadConnection(
					new URL( serverUrl + "eclipse_plugin/upload/" )) ;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if ( reader == null ) {
			System.out.println("===upload.连接服务器失败！\n\n请检查您的网络连接……");
			return;
			//TODO 返回 reader 统一处理，
		}
		switch ( reader.getMessage() ) {
			case ServerMessages.UploadSuccess:
				System.out.println("===上传成功！");
				break;
			case ServerMessages.UploadFaild:
				System.out.println("===上传失败！");
				break;
			default:
				System.out.println("===上传出现意外情况……");
				break;
		}
	}

}
