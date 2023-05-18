package com.simplejava.customer.service;

import com.simplejava.customer.entity.CustomerEntity;
import com.simplejava.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/10/2023
 * Time: 11:03 PM
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;

    @Override
    public CustomerEntity save(CustomerEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<CustomerEntity> findById(Long id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<CustomerEntity> findAll() {
        return (List<CustomerEntity>) repository.findAll();
    }

    @Override
    public CustomerEntity update(CustomerEntity entity, Long id) {
        Optional<CustomerEntity> optional = findById(id);
        if (optional.isPresent()) {
            return repository.update(entity,id);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }




}
