package com.przedtop.bank.system.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupEventListener {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("✅ Spring Boot is fully configured! Running post-startup tasks...");
        performStartupTasks();
    }

    private void performStartupTasks() {
        System.out.println("📥 Running post-initialization logic...");
    }
}
