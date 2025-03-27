/**
 * Kyle-Soft 保留所有权。
 * @author Kyle.Zhang
 */
package com.kyle.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;

/**
 * 扫描指定目录下jar包，列出不包含tld文件的包，并按Tomcat排除格式输出
 *
 */
public class TldScanner {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java -jar build/libs/Kyle-TldScanner-1.0.jar [jars' folder path]");
			System.exit(0);
		}
		String tomcatLibPath = args[0];
		Collection<File> jarFiles = FileUtils.listFiles(new File(tomcatLibPath), new String[]{"jar"}, false);
		List<File> tldFiles = new ArrayList<File>();
		
		for (File jarFile:jarFiles) {
			try {
				ZipFile zipFile = new ZipFile(jarFile);
				Enumeration<ZipEntry> zipEntries = (Enumeration<ZipEntry>)zipFile.entries();
				while (zipEntries.hasMoreElements()) {
					ZipEntry zipEntry = zipEntries.nextElement();
					if (zipEntry.getName().endsWith(".tld")) {
						tldFiles.add(jarFile);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("jars contains tlds: ");
		for (File jarFile : tldFiles) {
			System.out.println(jarFile.getName());
		}
		
		StringBuffer sbFull = new StringBuffer();
		StringBuffer sbLine = new StringBuffer();
		for (File jarFile:jarFiles) {
			if (!tldFiles.contains(jarFile)) {
				sbLine.append(jarFile.getName() + ",");
				if (sbLine.length() >= 72) {
					sbFull.append(sbLine.toString() + "\\\r\n");
					sbLine.setLength(0);
				}
			}
		}
		if (sbLine.length() > 0) {
			sbFull.append(sbLine.toString() + "\\\r\n");
			sbLine.setLength(0);
		}
		System.out.println();
		System.out.println("jars can be skipped: ");
		System.out.println(sbFull.toString());
	}

}
