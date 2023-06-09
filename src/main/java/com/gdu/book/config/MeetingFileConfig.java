package com.gdu.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MeetingFileConfig {

  @Bean
  public MultipartResolver multipartResolver() {  // Bean 타입은 MultipartResolver로 설정해야 한다.(인터페이스 타입)
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setDefaultEncoding("UTF-8");
    multipartResolver.setMaxUploadSize(1024 * 1024 * 100);       // 전체 첨부 파일의 크기 100MB
    multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 5); // 첨부 파일 하나의 크기 10MB
    return multipartResolver;
  }
  
}
