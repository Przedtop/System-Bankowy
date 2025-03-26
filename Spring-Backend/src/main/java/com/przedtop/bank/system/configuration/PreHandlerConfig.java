package com.przedtop.bank.system.configuration;

import com.przedtop.bank.system.entity.PreHandler;
import com.przedtop.bank.system.repozytories.PreHandlerRepo;
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
public class PreHandlerConfig implements HandlerInterceptor {

    private static final int maxRequestsPerSecond = 5;

    private final PreHandlerRepo repo;
    private static final Logger logger = LoggerFactory.getLogger(PreHandlerConfig.class);

    public PreHandlerConfig(PreHandlerRepo preHandlerRepo) {
        this.repo = preHandlerRepo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        PreHandler preHandler = new PreHandler();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String senderIp = getClientIp(request);
        String date = now.format(formatter);

        logger.info("Global interceptor triggered for: {} from IP: {} method: {}", request.getRequestURI(), senderIp, request.getMethod());
        logger.info("Handler: {}", handler);

        long lastRequestsCount = repo.countByDateBetweenAndStatus(now.minusSeconds(1).format(formatter), date, "OK");
        if (lastRequestsCount >= maxRequestsPerSecond) {
            logger.error("Too many requests");
            preHandler.setRequest_url(request.getRequestURI());
            preHandler.setSender_ip(senderIp);
            preHandler.setDate(date);
            preHandler.setStatus("FAIL");
            repo.save(preHandler);

            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
            return false;
        }


        preHandler.setRequest_url(request.getRequestURI());
        preHandler.setSender_ip(senderIp);
        preHandler.setDate(date);
        preHandler.setStatus("OK");

        repo.save(preHandler);
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