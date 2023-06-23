package com.gdu.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdu.book.intercept.LoginCheckInterceptor;
import com.gdu.book.util.MyFileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final MyFileUtil myFileUtil;
	private final LoginCheckInterceptor loginCheckInterceptor;
  
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imageLoad/**")
		.addResourceLocations("file:" + myFileUtil.getSummernoteImagePath() + "/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor)
			.addPathPatterns("/book/bookReviewadd.do")
			.addPathPatterns("/cart/addCart.do");
	}
  
}
