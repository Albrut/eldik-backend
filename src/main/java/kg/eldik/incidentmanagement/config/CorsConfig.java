package kg.eldik.incidentmanagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    private static final Logger log = LoggerFactory.getLogger(CorsConfig.class);

    @Value("${front.url}")
    private String frontUrl;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        log.info("Configuring CORS for frontUrl={}", frontUrl);
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of(frontUrl));
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(true);
        cfg.setExposedHeaders(List.of(
                "X-Auth-Token",
                "Content-Type",
                "Content-Length",
                "Date",
                "Server",
                "ETag",
                "Last-Modified",
                "Cache-Control",
                "Location",
                "Authorization",
                "X-RateLimit-Limit",
                "X-RateLimit-Remaining",
                "X-Request-ID"
        ));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);

        FilterRegistrationBean<CorsFilter> reg = new FilterRegistrationBean<>(new CorsFilter(source));
        reg.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return reg;
    }
}
