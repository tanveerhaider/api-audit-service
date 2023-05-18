package com.simplejava.filter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.simplejava.audit.model.Audit;
import com.simplejava.audit.service.AuditService;
import com.simplejava.audit.service.AuditServiceImpl;
import com.simplejava.util.ObjectToJson;
import com.simplejava.util.RequestUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/** This filter is written to audit api request
 * Description :
 * User: Tanveer Haider
 * Date: 5/14/2023
 * Time: 11:28 PM
 */
@Component
@AllArgsConstructor
@Slf4j
public class AuditFilter extends OncePerRequestFilter {

    private final RequestUtil requestUtil;
    private final AuditService auditService;

    @Qualifier("dbauditPool")
    private final ExecutorService executorService;

    private final ObjectToJson objectToJson;

   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startTime=System.currentTimeMillis();
        log.info("Inside Once Per Request Filter originated by request {}", request.getRequestURI());
        String correlationId = requestUtil.getCorrelationIdFromHeader(request);
        String requestTrackingId= requestUtil.getUniqueRequestId();
        response.addHeader(RequestUtil.TRANSACTION_ID_HEADER_NAME,requestTrackingId);
        response.addHeader(RequestUtil.CORRELATION_ID_HEADER_NAME,correlationId);
        filterChain.doFilter(request, response);
        long endTime=System.currentTimeMillis();
        Audit audit=new Audit(null,correlationId,requestTrackingId,request.getRequestURI(),request.getMethod(),
                response.getStatus(),
                startTime,endTime,(endTime-startTime));
        log.info("Audit::"+objectToJson.toJson(audit));
        persistAuditData(audit);
    }

    private CompletableFuture<Void> persistAuditData(Audit audit) {
        return CompletableFuture.runAsync( () ->{
            try {
                addData(audit);
            }catch(Exception ex) {
                log.error("Unable to add data to Db",ex);
            }
        }, this.executorService);

    }

    private void addData(Audit audit) {
        log.info("adding request audit data to db");
        auditService.addAudit(audit);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }
}
