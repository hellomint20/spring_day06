package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService {
	
	public String getMessage(String msg, String url) {
		String message = "<script>alert('"+msg+"');";
		message += "location.href='"+url+"';</script>";
		return message;
	}
	
	public String saveFile(MultipartFile file) {
		SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
		String sysFileName = fo.format(new Date());
		sysFileName = sysFileName +file.getOriginalFilename();
		File saveFile = new File(IMAGE_REPO+"/"+sysFileName); //폴더에 저장
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}
}
