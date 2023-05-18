package com.simplejava.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/3/2023
 * Time: 3:53 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto  {
    private long id;
    private String firstName;
    private String lastName;

    private String phoneNumber;
    @JsonProperty("emailAddress")
    private String emailId;
    private String ssn;


}

