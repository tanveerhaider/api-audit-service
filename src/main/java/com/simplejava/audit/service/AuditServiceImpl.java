package com.simplejava.audit.service;

import com.simplejava.audit.model.Audit;
import com.simplejava.audit.repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/14/2023
 * Time: 11:22 PM
 */

@Service
@AllArgsConstructor
public class AuditServiceImpl implements  AuditService{

    private final AuditRepository auditRepository;
    @Override
    public Audit addAudit(Audit audit) {
        Audit auditFromDb = auditRepository.save(audit);
        return auditFromDb;
    }
}
