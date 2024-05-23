package dev.backend.eduverse.middleware;

import dev.backend.eduverse.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TokenService tokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //register user requests interceptor
        registry.addInterceptor(new RequestInterceptor(tokenService))
                .addPathPatterns("/api/auth/**");// Intercept only requests to paths under /api/auth/

        //register admin requests interceptor
        registry.addInterceptor(new AdminRequestInterceptor(tokenService))
                .addPathPatterns("/api/auth/admin/**");
    }
}
