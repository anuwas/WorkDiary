package com.org.pack.wd.operation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.dto.OperationNameDescription;
import com.org.pack.wd.operation.entity.Operation;

@Repository
public interface OperationRepository extends PagingAndSortingRepository<Operation,Long>{
	
	
	
	@Query("select new com.org.pack.wd.dto.OperationNameDescription(operationId,operationName,operationDescription) from Operation where operationStatus = 'Active' order by operationCreatedDate DESC")
	List<OperationNameDescription> findAllOperationNameDescription();

}
