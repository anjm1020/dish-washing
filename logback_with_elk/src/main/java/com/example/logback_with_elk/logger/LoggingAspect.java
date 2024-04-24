package com.example.logback_with_elk.logger;

import jakarta.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

  private static final String HANDLER_INFO = "{0}.{1}({2})";
  private static final String REQUEST_INFO = "{0} {1}";
  private static final String LOG_FORMAT = "{}: {}";

  @Around("execution(* com.example.logback_with_elk.api.DishController.*(..))")
  public Object loggingAroundRequest(final ProceedingJoinPoint joinPoint) throws Throwable {

    String requestInformation = getRequestInformation();

    log.info(LOG_FORMAT, requestInformation, "START");
    Object retVal = joinPoint.proceed();
    log.info(LOG_FORMAT, requestInformation, "END");

    return retVal;
  }

  @Around("execution(* com.example.logback_with_elk.api.DishService.*(..))")
  public Object loggingAroundService(final ProceedingJoinPoint joinPoint) throws Throwable {

    String handlerInformation = getHandlerInformation(joinPoint);

    Object retVal = null;
    try {

      log.info(LOG_FORMAT, handlerInformation, "START");
      retVal = joinPoint.proceed();
      log.info(LOG_FORMAT, handlerInformation, "END");

    } catch (Throwable e) {
      log.error(LOG_FORMAT, handlerInformation, "ERROR");
      throw e;
    }

    return retVal;
  }

  private String getRequestInformation() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String method = request.getMethod();
    String uri = request.getRequestURI();
    return MessageFormat.format(REQUEST_INFO, method, uri);
  }

  private String getHandlerInformation(ProceedingJoinPoint joinPoint) {
    final String className = joinPoint.getTarget().getClass().getSimpleName();
    final String methodName = joinPoint.getSignature().getName();
    final String args = Arrays.toString(joinPoint.getArgs());

    return MessageFormat.format(HANDLER_INFO, className, methodName, args);
  }
}
