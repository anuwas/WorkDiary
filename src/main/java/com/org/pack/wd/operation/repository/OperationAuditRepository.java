package com.org.pack.wd.operation.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.operation.entity.Operation;
import com.org.pack.wd.operation.entity.OperationAudit;

@Repository
public interface OperationAuditRepository extends PagingAndSortingRepository<OperationAudit,Long>{
	List<OperationAudit> findByOperation(Operation operation);
}
