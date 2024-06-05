package dev.backend.eduverse.middleware;


import dev.backend.eduverse.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    public RequestInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Get token from Request Header
        String token = request.getHeader("Authorization");

        //No Token in request header or token doesn't exist in token table
        if (token == null || !tokenService.existsByToken(token)) {
            logger.info("Authentication Failed!");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AUTHENTICATION FAILED!");
            return false;
        }
        logger.info("Authentication Passed.");
        return true;
    }
}
