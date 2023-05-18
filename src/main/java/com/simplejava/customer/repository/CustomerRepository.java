package com.simplejava.customer.repository;

import com.simplejava.customer.entity.CustomerEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/3/2023
 * Time: 7:18 PM
 */
@Repository
public class CustomerRepository {

    private Map<Long, CustomerEntity> customerMap;

    @PostConstruct
    public void init() {
        List<CustomerEntity> allCustomers = new ArrayList<>();
        CustomerEntity customer1 = new CustomerEntity(1, "Amit", "Kumar",
                "630-901-1234", "amit@gmail.com", "191-23-7650");
        CustomerEntity customer2 = new CustomerEntity(2, "Robert", "Dean",
                "208-654-4321", "robermartin@gmail.com", "167-03-3908");
        CustomerEntity customer3 = new CustomerEntity(3, "Peter", "Martin",
                "647-567-9320", "peter@gmail.com", "234-89-9650");
        CustomerEntity customer4 = new CustomerEntity(4, "Sachin", "Kumar",
                "647-567-9320", "sachin@gmail.com", "505-89-5456");
        CustomerEntity customer5 = new CustomerEntity(5, "Ravi", "Singh",
                "647-567-9320", "ravi@gmail.com", "103-00-1234");

        allCustomers.add(customer1);
        allCustomers.add(customer2);
        allCustomers.add(customer3);
        allCustomers.add(customer4);
        allCustomers.add(customer5);
        customerMap= allCustomers.stream()
                .collect(Collectors.toConcurrentMap(CustomerEntity::getId, Function.identity()));
    }

    public CustomerEntity save(CustomerEntity customerEntity){
        Long generatedId = getId();
        customerEntity.setId(generatedId);
        customerMap.put(generatedId,customerEntity) ;
        return customerEntity;
    }

    public CustomerEntity update(CustomerEntity customerEntity,Long id){
        customerMap.put(id,customerEntity) ;
        customerEntity.setId(id);
        return customerEntity;
    }

    public CustomerEntity findById(long id) {
        return customerMap.getOrDefault(id,customerMap.get(1l) );
    }

    public List<CustomerEntity> findAll() {
        return new ArrayList(customerMap.values());

    }

     public void deleteById(long id){
        customerMap.remove(id);
    }

    private Long getId(){

        Map.Entry<Long, CustomerEntity> entryWithMaxKey = customerMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .findFirst()
                .get();

        return entryWithMaxKey.getKey() +1;
    }


}
