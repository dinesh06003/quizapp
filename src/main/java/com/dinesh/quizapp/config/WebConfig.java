package com.dinesh.quizapp.config;


import io.micrometer.observation.annotation.Observed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public  void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**").allowedOrigins("http://localhost:5173")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
//@Bean
//public WebMvcConfigurer corsConfigurer() {
//    return new WebMvcConfigurer() {
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**") // Allow all endpoints
//                    .allowedOrigins("http://localhost:5173") // Frontend origin
//                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
//                    .allowedHeaders("*") // Allow all headers
//                    .allowCredentials(true); // Allow credentials (if needed)
//        }
//    };
//}

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // Allow all origins (you can restrict this later)
        config.addAllowedMethod("*");        // Allow all HTTP methods
        config.addAllowedHeader("*");        // Allow all headers
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints
        return new CorsFilter(source);
    }

}
