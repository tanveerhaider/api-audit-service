package com.simplejava.audit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/14/2023
 * Time: 11:12 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "audit")
public class Audit {

    @Id
    private String id;

    @Field(name = "Correlation-Id")
    private String correlationId;

    @Field(name = "Request_Transaction-Id")
    private String transactionId;

    @Field(name = "Request_Uri")
    private String requestUri;

    @Field(name = "Request_Method")
    private String requestMethod;

    @Field(name = "Http_Status_Code")
    private int httpStatusCode;

    @Field(name = "Request_Start_Time")
    private long startTime;

    @Field(name = "Request_End_Time")
    private long endTime;

    @Field(name = "duration_in_ms")
    private long duration;


}
