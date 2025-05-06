package com.example.chat.chat_server.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답을 할때 json을 자바스크립트에서 처리할수 있게 할지 설정
//        config.addAllowedOrigin("*"); // 모든 ip 응답 허용
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*"); // 모든 hedaer 응답 허용
        config.addAllowedMethod("*"); // 모든 post,get,pu,delete,path 요청등 허용
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


}
