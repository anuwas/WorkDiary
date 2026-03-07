package com.org.pack.wd.scrum.repository;

import com.org.pack.wd.scrum.entity.Refinements;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefinementsRepository extends PagingAndSortingRepository<Refinements,Long>{
	List<Refinements> findAllByApplicationNameOrderByRefinementDateDesc(String applicationName, Pageable pageable);
	List<Refinements> findAllByOrderByCreatedDateDesc();
}
