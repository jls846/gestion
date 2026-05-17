package com.gestion_eventos.gestion.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 1. Permitimos de forma explícita los orígenes de desarrollo de React
        config.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:5173", 
                "http://127.0.0.1:5173"
        ));
        
        // 2. Habilitamos el paso de credenciales de manera segura
        config.setAllowCredentials(true);
        
        // 3. Declaramos explícitamente los encabezados permitidos en lugar del comodín de conflicto
        config.setAllowedHeaders(Arrays.asList(
                "Origin", 
                "Content-Type", 
                "Accept", 
                "Authorization", 
                "X-Requested-With"
        ));
        
        // 4. Métodos HTTP estándar para tu CRUD de eventos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Aplicamos esta configuración a absolutamente todas las rutas del sistema
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}