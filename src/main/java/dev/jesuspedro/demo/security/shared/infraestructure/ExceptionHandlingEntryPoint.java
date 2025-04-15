package dev.jesuspedro.demo.security.shared.infraestructure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class ExceptionHandlingEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        log.warn("Exception: {} - Message: {}",authException.getClass(),authException.getMessage());
        String jsonResponse = """
            {
                "timestamp": "%s",
                "error": "Unauthorized",
                "message": "Credenciales inválidas"
            }
            """.formatted(LocalDateTime.now());
        response.getWriter().write(jsonResponse);
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}

