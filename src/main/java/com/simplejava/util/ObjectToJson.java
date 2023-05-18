package com.simplejava.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description : Convert Object to Json Representation with Pretty print
 * User: Tanveer Haider
 * Date: 5/17/2023
 * Time: 10:37 PM
 */
@Component
@Slf4j
@AllArgsConstructor
public class ObjectToJson {

    private final ObjectMapper objectMapper;

    @PostConstruct
    private void init(){
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String toJson(Object obj){
        try{
            return objectMapper.writeValueAsString(obj);
        }catch(JsonProcessingException ex){
            log.warn("unable to convert object to JSON",ex);
        }
        return "";
    }
}
