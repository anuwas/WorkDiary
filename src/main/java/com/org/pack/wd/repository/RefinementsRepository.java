package com.org.pack.wd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.entity.Refinements;

@Repository
public interface RefinementsRepository extends PagingAndSortingRepository<Refinements,Long>{
	List<Refinements> findAllByApplicationNameOrderByRefinementDateDesc(String applicationName, Pageable pageable);
}
