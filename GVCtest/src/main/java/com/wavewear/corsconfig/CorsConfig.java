package com.wavewear.corsconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 오리진 (프론트엔드 애플리케이션의 도메인)
        config.addAllowedOrigin("http://localhost:3000"); // 프론트엔드 애플리케이션의 도메인으로 변경

        // 필요한 CORS 설정 추가 (HTTP 메서드, 헤더 등)

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
