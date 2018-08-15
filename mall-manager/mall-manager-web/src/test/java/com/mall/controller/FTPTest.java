package com.mall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {

	@Test
	public void testFTP() throws SocketException, IOException {
		//创建FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建Ftp连接,第二个参数可没有，默认端口是21
		ftpClient.connect("192.168.175.130",21);
		//登陆ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//设置存储路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		File file = new File("C:\\Users\\ren1\\Pictures\\Saved Pictures\\FirstLove.mp4_20180609_092801.274.jpg");
		FileInputStream fileInputStream = new FileInputStream(file);
		//上传文件
		ftpClient.storeFile(file.getName(),fileInputStream);
		
		//关闭连接
		ftpClient.logout();
	}
	
	
	
}
