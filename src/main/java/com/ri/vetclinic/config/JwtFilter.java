package com.ri.vetclinic.config;

import com.ri.vetclinic.service.VetUserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private VetUserService vetUserService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        if (!request.getRequestURI().startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String accessToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            accessToken = requestTokenHeader.substring(7);
            try {
                username = jwtService.extractUsername(accessToken);
                logger.debug("JWT Token found in Authorization header, username: " + username);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token from Authorization header");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token from Authorization header has expired");
                username = e.getClaims().getSubject();
            }
        }

        if (accessToken == null) {
            accessToken = getTokenFromCookie(request, "accessToken");
            if (accessToken != null) {
                try {
                    username = jwtService.extractUsername(accessToken);
                    logger.debug("JWT Token found in cookie, username: " + username);
                } catch (IllegalArgumentException e) {
                    logger.error("Unable to get JWT Token from cookie");
                } catch (ExpiredJwtException e) {
                    logger.error("JWT Token from cookie has expired");
                    username = e.getClaims().getSubject();
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = vetUserService.loadUserByUsername(username);

                if (accessToken != null && jwtService.validateToken(accessToken, userDetails)) {
                    setAuthentication(request, userDetails);
                    logger.debug("Authentication set for user: " + username);
                } else {
                    String refreshToken = getTokenFromCookie(request, "refreshToken");
                    if (refreshToken != null && jwtService.validateToken(refreshToken, userDetails)) {
                        String newAccessToken = jwtService.generateAccessToken(username);

                        Cookie newAccessCookie = new Cookie("accessToken", newAccessToken);
                        newAccessCookie.setHttpOnly(true);
                        newAccessCookie.setPath("/");
                        newAccessCookie.setMaxAge(60 * 15); // 15 минут

                        response.addCookie(newAccessCookie);

                        setAuthentication(request, userDetails);
                        logger.debug("New access token generated and authentication set for user: " + username);
                    } else {
                        logger.debug("Both access and refresh tokens are invalid for user: " + username);
                    }
                }
            } catch (Exception e) {
                logger.error("Error loading user details for username: " + username, e);
            }
        }

        chain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth/") || path.startsWith("/api/public/");
    }

    private String getTokenFromCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}