package dev.jesuspedro.demo.security.shared.infraestructure;

import dev.jesuspedro.demo.security.shared.application.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String keyHeader = "Authorization";
        String valueHeaderPrefix = "Bearer ";

        final String valueHeader = request.getHeader(keyHeader);

        if(valueHeader == null || !valueHeader.startsWith(valueHeaderPrefix)){
                filterChain.doFilter(request, response);
                return;
        }

        final String authToken = valueHeader.replace(valueHeaderPrefix, Strings.EMPTY);
        String username = jwtTokenManager.getUsernameFromToken(authToken);

        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final boolean canStartTokenValidation = Objects.nonNull(username) && Objects.isNull(securityContext.getAuthentication());

        if (!canStartTokenValidation) {
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails user = userDetailsService.loadUserByUsername(username);

        if (!jwtTokenManager.validateToken(authToken, user.getUsername())) {
            filterChain.doFilter(request, response);
            return;
        }



        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(authentication);

        log.info("Authentication successful. Logged in username : {} ", username);

        filterChain.doFilter(request, response);
    }
}
