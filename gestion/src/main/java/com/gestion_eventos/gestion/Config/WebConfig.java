package com.gestion_eventos.gestion.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Permitimos de forma explícita los orígenes de desarrollo de React
        config.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:5173", 
                "http://127.0.0.1:5173"
        ));
        
        //Habilitamos el paso de credenciales de manera segura
        config.setAllowCredentials(true);
        
        // Declaramos explícitamente los encabezados permitidos en lugar del comodín de conflicto
        config.setAllowedHeaders(Arrays.asList(
                "Origin", 
                "Content-Type", 
                "Accept", 
                "Authorization", 
                "X-Requested-With"
        ));
        
        // Métodos HTTP estándar para tu CRUD de eventos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Aplicamos esta configuración a absolutamente todas las rutas del sistema
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}