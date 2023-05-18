package com.simplejava.customer.service;

import java.util.List;
import java.util.Optional;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/10/2023
 * Time: 10:59 PM
 */
public interface GenericService <E, M>{

    E save(E entity);
    Optional<E> findById(M id);


    List<E> findAll();

    void deleteById(M id);

    E update(E entity, M id);

}
