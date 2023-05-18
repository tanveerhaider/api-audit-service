package com.simplejava.audit.repository;

import com.simplejava.audit.model.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/14/2023
 * Time: 11:21 PM
 */
public interface AuditRepository extends MongoRepository<Audit, String> {
}
