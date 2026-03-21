package com.gestionbourse.config;

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
        config.addAllowedOrigin("*"); // Autorise toutes les origines, à ajuster en production
        config.addAllowedHeader("*"); // Autorise tous les en-têtes
        config.addAllowedMethod("GET"); // Autorise les requêtes GET
        config.addAllowedMethod("POST"); // Autorise les requêtes POST, etc.
        config.addAllowedMethod("PUT"); // Autorise les requêtes POST, etc.
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
