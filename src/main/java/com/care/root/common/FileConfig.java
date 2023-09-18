package com.care.root.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		
		mr.setMaxUploadSize(52428800); //50MB. 업로드 파일에 대한 사이즈 설정
		mr.setDefaultEncoding("utf-8");
		return mr;
	}
}
