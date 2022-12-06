package com.gdu.app02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMvcConfig implements WebMvcConfigurer {
	
	@Override // addResourceHandlers : servlet-context의 resource들을 추가할 수 있다. (interceptor는 따로 있음)
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// registry에 등록
		registry.addResourceHandler("/load/image/**")
			.addResourceLocations("file:///C:/summernoteImage/"); // 편하게 쓰려면 문자열 적어줄 수 있는 것 선택
		
		
	}
	
	
	
}
