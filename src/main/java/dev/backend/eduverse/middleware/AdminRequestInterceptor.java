package dev.backend.eduverse.middleware;

import dev.backend.eduverse.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminRequestInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(AdminRequestInterceptor.class);

    public AdminRequestInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Get token from Request Header
        String token = request.getHeader("Authorization");

        //No Token in request header or token doesn't exist in token table and token is not admin token
        if (token == null || !tokenService.existsByTokenAndIsAdmin(token)) {
            logger.info("Admin Authentication Failed!");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AUTHENTICATION FAILED!");
            return false;
        }
        logger.info("Admin Authentication Passed.");

        return true;
    }
}
