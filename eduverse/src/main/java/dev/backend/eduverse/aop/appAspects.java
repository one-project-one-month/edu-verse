package dev.backend.eduverse.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class appAspects {

	public static final Logger logger = LoggerFactory.getLogger(appAspects.class);
	
//  AdminRoleAspects
	@Before("execution(* dev.backend.*.*.*.AdminServiceImpl.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Enter Method : {} ", methodName);

		if ("getAllAdminRole".equals(methodName)) {
			logger.info("Entering the get all ADMIN role process");
		} else if ("updateAdminRole".equals(methodName)) {
			Object[] args = joinPoint.getArgs();
			Long id = (Long) args[1];
			logger.info("Updating adminRole with ID: {}" + id);
		} else if ("deleteAdminRole".equals(methodName)) {
			Long id = (Long) joinPoint.getArgs()[0];
			logger.info("Deleting adminRole with ID: {}" + id);
		}
	}

	@AfterReturning(pointcut = "execution(* dev.backend.*.*.*.AdminServiceImpl.*(..))", returning = "result")
	public void afterReturningMethod(JoinPoint joinPoint, boolean result) {
		Long id = (Long) joinPoint.getArgs()[0];

		if (result) {
			logger.info("Admin role with Id : {} deleted successfully.", id);
		} else {
			logger.info("Failed to delete ADMIN role with ID: {}", id);
		}
	}

	@AfterThrowing(pointcut = "execution(* dev.backend.*.*.*.AdminServiceImpl.*(..))", throwing = "error")
	public void afterThrowingMethod(JoinPoint joinPoint, Throwable error) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Exception in method : {} with cuase : {}", methodName, error.getMessage());
	}

}
