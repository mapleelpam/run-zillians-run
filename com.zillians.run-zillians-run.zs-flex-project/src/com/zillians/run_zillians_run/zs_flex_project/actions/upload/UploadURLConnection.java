package com.zillians.run_zillians_run.zs_flex_project.actions.upload;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UploadURLConnection {

	static UploadURLConnection conn = null;

	private String sessionid = "";
	private String csrftoken = "";
	private String inResponseSetCookieStr = "";
	private String cookiesStr = "";

	private UploadURLConnection() {

	}

	static public UploadURLConnection getInit() {
		if (conn == null) {
			conn = new UploadURLConnection();
		}
		return conn;
	}

	public UploadDialogXmlReader firstConnection(URL url) {
		HttpURLConnection conn = initOneConnection(url);

		UploadDialogXmlReader reader = analysisResponse(conn);

		conn.disconnect();

		return reader;
	}

	public UploadDialogXmlReader loginConnection(URL url, String username,
			String password) {

		HttpURLConnection conn = null;
		UploadDialogXmlReader reader = null;
		try {

			conn = (HttpURLConnection) url.openConnection();
			// Output to the connection. Default is
			// false, set to true because post
			// method must write something to the
			// connection
			// 设置是否向connection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true
			conn.setDoOutput(true);
			// Read from the connection. Default is true.
			conn.setDoInput(true);
			// Set the post method. Default is GET
			conn.setRequestMethod("POST");
			// Post cannot use caches
			// Post 请求不能使用缓存
			conn.setUseCaches(false);
			// This method takes effects to
			// every instances of this class.
			// URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
			// connection.setFollowRedirects(true);

			// This methods only
			// takes effacts to this
			// instance.
			// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			conn.setInstanceFollowRedirects(true);
			// Set the content type to urlencoded,
			// because we will write
			// some URL-encoded content to the
			// connection. Settings above must be set before connect!
			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
			// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
			// 进行编码
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect。

			conn.setRequestProperty("Cookie", cookiesStr);
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			// The URL-encoded contend
			// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
			String content = "username=" + username + "&password=" + password
					+ "&team_name=default&csrfmiddlewaretoken=" + csrftoken;
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(content);

			out.flush();
			out.close(); // flush and close

			conn.connect();

			reader = analysisResponse(conn);

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// HttpURLConnection co = null;
		// try {
		// co = initOneConnection( new
		// URL("http://192.168.1.1:8001/p/p2/upload/") );
		// String cookieStr = "sessionid=" + sessionid + ";csrftoken=" +
		// csrftoken;
		// co.setRequestProperty("Cookie", cookieStr);
		// co.connect();
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		//
		// BufferedReader rrr;
		// try {
		// rrr = new BufferedReader(new InputStreamReader(co.getInputStream()));
		// String line;
		// System.out.println("=============================");
		// System.out.println("Contents of post request");
		// System.out.println("=============================");
		// while ((line = rrr.readLine()) != null) {
		// System.out.println(line);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// co.disconnect();
		return reader;
	}

	public UploadDialogXmlReader getProjectsConnection(URL url) {

		HttpURLConnection conn = null;
		UploadDialogXmlReader reader = new UploadDialogXmlReader();
		try {

			URLConnection c = url.openConnection();
			System.out.println("getget: " + c.getClass());
			conn = (java.net.HttpURLConnection) c;

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Cookie", cookiesStr);
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");

			conn.connect();

			reader = analysisResponse(conn);

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	public UploadDialogXmlReader openProjectConnection(URL url) {
		HttpURLConnection conn = null;

		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Cookie", cookiesStr);
			System.out.println("===cookie:" + cookiesStr);
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
			conn.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		UploadDialogXmlReader reader = analysisResponse(conn);

		conn.disconnect();

		return reader;
	}

	public UploadDialogXmlReader uploadConnection(URL url) {
		
		UploadZipTool zipTool = UploadZipTool.getInit();
		zipTool.compress();
		
		java.net.HttpURLConnection conn = null;
		UploadDialogXmlReader reader = null;

		String boundary = "---------------------------2921238217421";
		String fileUrl = zipTool.outputDir + zipTool.outputFileName;

		try {

			conn = (HttpURLConnection) new URL(
				"http://192.168.1.1:8001/eclipse_plugin/upload/")
				.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);
			conn.setRequestProperty("Cookie", cookiesStr);

			OutputStream out = conn.getOutputStream();

			String header = "--" + boundary + "\r\n"
					+ "Content-Disposition: form-data; name=\"csrfmiddlewaretoken\"\r\n"
					+ "\r\n"
					+ csrftoken + "\r\n"
					+ "--" + boundary + "\r\n"
					+ "Content-Disposition: form-data; name=\"file_name\"; filename=\"xyz.txt\"\r\n"
					+ "Content-Type: application/octet-stream\r\n" 
					+ "\r\n";
			out.write(header.getBytes());
			
			FileInputStream fis = new FileInputStream(fileUrl);
			
			//TODO 判断文件非空
			
			byte[] buffer = new byte[1024];
			
			int len = fis.read(buffer, 0, 1024);
			while ( len != -1) {
				out.write(buffer, 0, len);
				len = fis.read(buffer, 0, 1024);
			}
			
			fis.close();
			
			String footer = "\r\n"
					+ "--" + boundary + "--";

			out.write(footer.getBytes());
			
			out.flush();
			out.close(); // flush and close

			conn.connect();

			reader = analysisResponse(conn);

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reader;
	}

	private HttpURLConnection initOneConnection(URL url) {

		HttpURLConnection conn = null;
		try {
			// url = new
			// URL("http://192.168.1.1:8001/eclipse_plugin/get_projects/");

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setUseCaches(true);
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
			conn.setRequestProperty("Charsert", "UTF-8");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;
	}

	private UploadDialogXmlReader analysisResponse(HttpURLConnection conn) {
		try {
			if (conn.getResponseCode() != 200) {
				System.out.println("===getResponseCode 非200 错误！-"
						+ conn.getResponseCode());
				return null;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		UploadDialogXmlReader reader = new UploadDialogXmlReader();

		// 处理返回的xml文件
		try {
			if (reader.analysisXml(conn.getInputStream())) {
				System.out.println("===解析返回的xml文件成功！");
			} else {
				System.out.println("===解析返回的xml文件失败！");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// response header 处理
		doHeaderFieldTasks(conn.getHeaderFields());

		return reader;
	}

	/**
	 * 每次访问后，检查 response header 中的 Set-Cookie 值，并进行相应的设置
	 * 
	 * @param headerFields
	 */
	private void doHeaderFieldTasks(Map<String, List<String>> headerFields) {

		if (headerFields.get("Set-Cookie") == null) {
			System.out.println("=== Set-Cookie is null.");
			return;
		}

		inResponseSetCookieStr = headerFields.get("Set-Cookie").toString();

		StringBuffer sb = new StringBuffer();

		int indexSessionId = inResponseSetCookieStr.indexOf("sessionid");
		if (indexSessionId != -1) {
			String tmp = inResponseSetCookieStr.substring(indexSessionId + 10,
					indexSessionId + 10 + 32);
			if (!sessionid.equals(tmp)) {
				sessionid = tmp;
				System.out.println("===sessionid changed:" + tmp + "|"
						+ sessionid);
			}
		}
		sb.append("sessionid=" + sessionid + "; ");

		int indexCsrftoken = inResponseSetCookieStr.indexOf("csrftoken");
		if (indexCsrftoken != -1) {
			String tmp = inResponseSetCookieStr.substring(indexCsrftoken + 10,
					indexCsrftoken + 10 + 32);
			if (!csrftoken.equals(tmp)) {
				csrftoken = tmp;
				System.out.println("===csrftoken changed:" + tmp + "|"
						+ csrftoken);

			}
		}
		sb.append("csrftoken=" + csrftoken + "; ");

		cookiesStr = sb.toString();
	}
}
