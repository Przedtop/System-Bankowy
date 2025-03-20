package com.przedtop.bank.system.configuration;

import com.przedtop.bank.system.entity.PreHandleWebInceptor;
import com.przedtop.bank.system.repozytories.PreHandleWebInceptorRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PreHandleWebInceptorConfig implements HandlerInterceptor {

    private static final int maxRequestsPerSecond = 1;

    private final PreHandleWebInceptorRepo repo;
    private static final Logger logger = LoggerFactory.getLogger(PreHandleWebInceptorConfig.class);

    public PreHandleWebInceptorConfig(PreHandleWebInceptorRepo preHandleWebInceptorRepo) {
        this.repo = preHandleWebInceptorRepo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        PreHandleWebInceptor inceptor = new PreHandleWebInceptor();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String senderIp = getClientIp(request);
        String date = now.format(formatter);

        long lastRequestsCount = repo.countByDateBetweenAndStatus(now.minusSeconds(1).format(formatter), date, "OK");
        if (lastRequestsCount >= maxRequestsPerSecond) {
            logger.error("Too many requests");
            inceptor.setRequest_url(request.getRequestURI());
            inceptor.setSender_ip(senderIp);
            inceptor.setDate(date);
            inceptor.setStatus("FAIL");
            repo.save(inceptor);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
            return false;
        }


        inceptor.setRequest_url(request.getRequestURI());
        inceptor.setSender_ip(senderIp);
        inceptor.setDate(date);
        inceptor.setStatus("OK");

        repo.save(inceptor);

        logger.info("Global interceptor triggered for: {} from IP: {} method: {}", request.getRequestURI(), senderIp, request.getMethod());
        logger.info("Handler: {}", handler.toString());
        return true;
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }
}