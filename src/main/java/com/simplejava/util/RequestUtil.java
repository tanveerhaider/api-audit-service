package com.simplejava.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/14/2023
 * Time: 11:39 PM
 */

@Component
@Slf4j
public class RequestUtil {

    public static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";
    public static final String TRANSACTION_ID_HEADER_NAME = "tranaction-id";

    public String getCorrelationIdFromHeader(final HttpServletRequest httpServletRequest) {
        String correlationId = httpServletRequest.getHeader(CORRELATION_ID_HEADER_NAME);
        log.info("correlationId :: " + correlationId);
        if (null==correlationId || correlationId.isBlank()) {
            correlationId = generateUniqueId();
        }
        return correlationId;
    }

    public String getUniqueRequestId(){
        return generateUniqueId();
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

}
