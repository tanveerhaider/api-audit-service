package com.simplejava.customer.controller;

import com.simplejava.customer.dto.CustomerDto;
import com.simplejava.customer.entity.CustomerEntity;
import com.simplejava.customer.service.CustomerService;
import com.simplejava.customer.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/3/2023
 * Time: 7:37 PM
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id)  {
        Optional<CustomerEntity> customerById = customerService.findById(id);
        CustomerDto customerDto = CustomerMapper.MAPPER.mapToDto(customerById.get());
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findBAll() {
        List<CustomerEntity> customerEntities= customerService.findAll();
        List<CustomerDto> customerDtoList = customerEntities.stream().map(t ->CustomerMapper.MAPPER.mapToDto(t))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerDtoList);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customer) {
        CustomerEntity customerEntity = CustomerMapper.MAPPER.mapToEntity(customer);
        CustomerEntity customerFromDB = customerService.save(customerEntity);
        CustomerDto customerDto = CustomerMapper.MAPPER.mapToDto(customerFromDB);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(customerFromDB.getId()).toUri();
        log.info("Uri:" + location);
        return ResponseEntity.created(location)
                .body(customerDto);


    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,
                                                      @RequestBody CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.MAPPER.mapToEntity(customerDto);
        CustomerEntity customer = customerService.update(customerEntity,id);
        CustomerDto customerResponse = CustomerMapper.MAPPER.mapToDto(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletetCustomer(@PathVariable Long id) {
        log.info("Delete customer with id:" + id);
        customerService.deleteById(id);
        return ResponseEntity.ok().body("customer with id " + id + " deleted successfully");
    }



}
