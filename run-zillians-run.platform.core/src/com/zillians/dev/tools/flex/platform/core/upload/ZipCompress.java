/** ZipCompress 1.0 
 * java 原生（java.util.zip )压缩解压实现，
 * 支持目录或文件压缩，支持空目录，
 * 
 * 已知Bug：压缩后用 winrar 解压 中文文件（夹）名乱码
 * 但文件内容正常，而且使用本类自带 unzip 不会乱码
 * 
 * CREATOR: Merlyle [merlyle@gmail.com]
 * DATE:    2011-3-10
 */
package com.zillians.dev.tools.flex.platform.core.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 */
public class ZipCompress {

	private static final int	BUFFER = 1024;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String srcFolder		= "D:/work/somefolder";
		final String generateZip	= "D:/work/generate.zip";
		final String otherFolder	= "D:/work/othefolder";
		
		ZipCompress z = new ZipCompress();
		
		z.zip( srcFolder , generateZip );
		z.unzip( generateZip, otherFolder );

	}
	

	/** zip 压缩方法
	 * @param sourceFileStr 要压缩的目录或文件
	 * @param outputFileUrl zip 文件完整输出输入,如:D:/demo.zip
	 */
	public boolean zip(String sourceFileStr, String outputFileUrl ) {
		File sourceFile = new File( sourceFileStr );
		if ( !sourceFile.exists() ) {
			System.out.println("--------路径或文件不存在！正在退出……\n");
			return false;
		}
		
		try {
			CheckedOutputStream cos = new CheckedOutputStream(
					new FileOutputStream( outputFileUrl ), new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			
			System.out.println("--------验证文件或路径成功。------------\n--------|" 
					+ sourceFileStr 
					+ "|\n-------- vVvVvVvVvVvVvVv\n--------|" 
					+ outputFileUrl 
					+ "|\n--------开始压缩:-----------------------");
			addFile(out, sourceFile, "");
			out.close();
			System.out.println("--------压缩完成。请查看:---------------\n--------" 
					+ outputFileUrl + "\n");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	/** 添加压缩文件
	 * @param out zip输出流
	 * @param sourceFile 要压缩的文件或目录
	 * @param path 要压缩的文件在 zip 中的路径
	 * @throws IOException
	 */
	private void addFile (ZipOutputStream out, File sourceFile, String path) throws IOException {
		if ( sourceFile.isDirectory() ) {
			path += sourceFile.getName() + "/";

			File[] files = sourceFile.listFiles();
			if ( files.length == 0) {
				System.out.println("--------add empty folder:" + path);
				out.putNextEntry(new ZipEntry( path ) );
				return;
			}
			for ( File f : files ) {
				//对目录文件递归调用压缩
				addFile (out, f, path);
			}
		}
		else if ( sourceFile.isFile() ) {
			System.out.println("--------add file:" + path + sourceFile.getName());
			
			//设置压缩路径、文件名，并写入
			out.putNextEntry(new ZipEntry( path + sourceFile.getName() ));
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream( sourceFile));
			int count;  
			byte data[] = new byte[BUFFER];  
			while ((count = bis.read(data, 0, BUFFER)) != -1) {  
			    out.write(data, 0, count); 
			}
			bis.close();
		}
	}
	
	/** zip 解压方法 
	 * @param zipFileUrl 要解压的 zip 文件绝对路径
	 * @param outputDir 解压文件的输出路径（可不存在）
	 * @return
	 */
	public boolean unzip (String zipFileUrl, String outputDir) {
		File zipFile = new File(zipFileUrl);
		if ( !zipFile.exists() ) {
			System.out.println("--------zip 文件不存在！正在退出……\n");
			return false;
		}
		
		try {
			CheckedInputStream ios = new CheckedInputStream(
					new FileInputStream(zipFile), new CRC32());
			ZipInputStream in = new ZipInputStream(ios); 
			
			System.out.println("--------验证文件成功。------------------\n--------|" 
					+ zipFileUrl 
					+ "|\n-------- A^A^A^A^A^A^A^A\n--------|" 
					+ outputDir 
					+ "|\n--------开始解压:-----------------------");
			
			ZipEntry entry = null;
			
			//循环取出 zip 中的所有 entry
			while ( ( entry = in.getNextEntry() ) != null) {
				File outputFile = new File(outputDir + "/" + entry.getName());
				
				if ( entry.isDirectory() ) {
					System.out.println("--------create folder:" + outputFile);
					outputFile.mkdirs();
				}
				else {
					
					//文件路径检测是否存在
					File folder = outputFile.getParentFile();
					if ( !folder.exists() ) {
						System.out.println("--------create folder:" + folder);
						folder.mkdirs();
					}
					
					System.out.println("--------create file:" + outputFile);
					
					BufferedOutputStream bos = new BufferedOutputStream (
							new FileOutputStream( outputFile ));
					int count;  
					byte data[] = new byte[BUFFER];  
					while ((count = in.read(data, 0, BUFFER)) != -1) {  
					    bos.write(data, 0, count); 
					}
					bos.close();
				}
				

			}
			
			in.close();
			
			System.out.println("--------解压完成。请查看:---------------\n--------" 
					+ outputDir + "\n");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	

}
