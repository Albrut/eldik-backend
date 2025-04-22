package kg.eldik.incidentmanagement.config;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.eldik.incidentmanagement.service.ZabbixApiService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final ZabbixApiService zabbixApiService;
    public AuthTokenFilter(ZabbixApiService zabbixApiService) {
        this.zabbixApiService = zabbixApiService;
    }



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return path.startsWith("/api/v1/login")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {



        String token = request.getHeader("X-Auth-Token");
        if (token == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "No token");
            return;
        }

        JsonNode check = zabbixApiService.getUserInfo(token);
        if (check == null || check.has("error")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
