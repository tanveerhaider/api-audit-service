package com.simplejava.async.config;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/** Custom Thread Pool configuration class . We can define multiple configuration depending on differrent
 * async tasks
 * Description :
 * User: Tanveer Haider
 * Date: 5/16/2023
 * Time: 11:13 PM
 */
@Component
@Slf4j
public class ExecutorServiceConfig {
    private final ThreadFactory auditDBthreadFactory = new CustomizableThreadFactory("dbaudit-");
    private ExecutorService executorForAddingAudit;

    @Value("${audit.thread.poolsize:20}")
    private int auditThreadPoolSize;

    @Bean("dbauditPool")
    public ExecutorService s3UploadThreadPool() {
        this.executorForAddingAudit = Executors.newFixedThreadPool(auditThreadPoolSize, auditDBthreadFactory);
        return this.executorForAddingAudit;
    }

    @PreDestroy()
    public void destroyCustomTHreadPools() {
        this.executorForAddingAudit.shutdown();
        log.info("DB audit Thread Pool Destroy");

    }

}
