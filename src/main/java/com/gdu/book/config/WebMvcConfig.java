package com.gdu.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdu.book.util.MyFileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final MyFileUtil myFileUtil;
  
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imageLoad/**")
		.addResourceLocations("file:" + myFileUtil.getSummernoteImagePath() + "/");
	}
  
}
