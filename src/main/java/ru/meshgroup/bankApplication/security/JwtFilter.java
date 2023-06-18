package ru.meshgroup.bankApplication.security;

import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.meshgroup.bankApplication.exception.UserNotFoundException;
import ru.meshgroup.bankApplication.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final HandlerExceptionResolver resolver;

    public JwtFilter(JwtProvider jwtProvider, UserService userService, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = getTokenFromRequest(request);
            if (token == null || !jwtProvider.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            try {
                String userId = jwtProvider.getLoginFromToken(token);
                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = new CustomUserDetails(userService.getUserById(Long.valueOf(userId)));
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                filterChain.doFilter(request, response);
            } catch (PermissionDeniedDataAccessException | UserNotFoundException e) {
                resolver.resolveException(request, response, null, e);
            }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return
                request.getServletPath().equals("/api/user/") && request.getMethod().equals(HttpMethod.GET.name()) ||
                request.getServletPath().equals("/api/user/") && request.getMethod().equals(HttpMethod.POST.name()) ||
                request.getServletPath().equals("/api/auth/");
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (Strings.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}