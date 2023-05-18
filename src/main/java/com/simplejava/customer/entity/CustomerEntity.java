package com.simplejava.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  Description :
 * User: Tanveer Haider
 * Date: 5/3/2023
 * Time: 7:16 PM
 *
 */
@Data
@AllArgsConstructor
public class CustomerEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String emailId;
    private String ssn;

}
