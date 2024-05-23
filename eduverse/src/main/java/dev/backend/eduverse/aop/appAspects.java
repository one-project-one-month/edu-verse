package dev.backend.eduverse.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class appAspects {

	public static final Logger logger = LoggerFactory.getLogger(appAspects.class);

	// AdminRoleAspects
	@Pointcut("execution(* dev.backend.*.*.*.AdminServiceImpl.*(..))")
	public void AdminRoleServiceMethods() {
	}

	@Before("AdminRoleServiceMethods()")
	public void beforeAdminRoleMethod(JoinPoint joinPoint) {
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

	@AfterReturning(pointcut = "AdminRoleServiceMethods()", returning = "result")
	public void afterReturningAdminRoleMethod(JoinPoint joinPoint, boolean result) {
		Long id = (Long) joinPoint.getArgs()[0];

		if (result) {
			logger.info("Admin role with Id : {} deleted successfully.", id);
		} else {
			logger.info("Failed to delete ADMIN role with ID: {}", id);
		}
	}

	@AfterThrowing(pointcut = "AdminRoleServiceMethods()", throwing = "error")
	public void afterThrowingAdminRoleMethod(JoinPoint joinPoint, Throwable error) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Exception in method : {} with cuase : {}", methodName, error.getMessage());
	}

	// AuthServiceAspects
	@Pointcut("execution(* dev.backend..AuthServiceImpl.*(..))")
	public void AuthServiceMethods() {
	}

	@Before("AuthServiceMethods()")
	public void beforeAuthMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		switch (methodName) {
		case "processUserLogin":
			logger.info("Processing User Login");
			break;
		case "processAdminLogin":
			logger.info("Processing Admin Login");
			break;
		case "logout":
			logger.info("Processing Logout");
			break;
		default:
			break;
		}
	}

	@AfterReturning(pointcut = "AuthServiceMethods()", returning = "result")
	public void afterReturningAuthMethod(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		switch (methodName) {
		case "processUserLogin":
			logger.info("Successfully finished user Login process");
			break;
		case "processAdminLogin":
			logger.info("Successfully finished admin Login process");
			break;
		case "logout":
			if ((boolean) result) {
				logger.info("Logout Success");
			} else {
				logger.info("Logout Failed");
			}
			break;
		default:
			break;
		}
	}

	@AfterThrowing(pointcut = "AuthServiceMethods()", throwing = "error")
	public void afterThrowingAuthMethod(JoinPoint joinPoint, Throwable error) {
		String methodName = joinPoint.getSignature().getName();

		switch (methodName) {
		case "processUserLogin":
			logger.info("User Login Failed");
			break;
		case "processAdminLogin":
			logger.info("Admin Login Failed");
			break;
		}
	}

}
