package com.mall.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mall.common.utils.FtpUtil;
import com.mall.common.utils.IDUtils;
import com.mall.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${FTP_HOST}")
	private String host;
	
	@Value("${FTP_PORT}")
	private int port;
	
	@Value("${FTP_USERNAME}")
	private String username;
	
	@Value("${FTP_PASSWORD}")
	private String password;
	
	@Value("${FTP_BASE_PATH}")
	private String basePath;
	
	@Value("${IMAGE_BASE_URL}")
	private String baseUrl;
	
	
	@Override
	public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if (null == uploadFile || uploadFile.isEmpty()) {
			resultMap.put("error", 1);
			resultMap.put("message","图片为空" );
			return resultMap;
		}
		
		//处理文件名
		String originalFileName = uploadFile.getOriginalFilename(); 
		String extFileName = originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = IDUtils.genImageName() + extFileName;
		
		//获取存储路径
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("/yyyy/MM/dd");

		//上传图片
		try {
			FtpUtil.uploadFile(host, port, username, password, basePath, filePath, newFileName, uploadFile.getInputStream());
			
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message","图片上传失败" );
			return resultMap;
		}
		resultMap.put("error", 0);
		resultMap.put("url", baseUrl + filePath + "/" + newFileName);
		
		return resultMap;
	}

}
