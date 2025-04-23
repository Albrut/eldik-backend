package kg.eldik.incidentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);      // IP, сессия
        filter.setIncludeQueryString(true);     // параметры ?a=1&b=2
        filter.setIncludePayload(true);         // тело запроса (до maxPayloadLength)
        filter.setIncludeHeaders(true);         // все заголовки
        filter.setMaxPayloadLength(10000);      // не захватывать слишком большие тела
        return filter;
    }
}
