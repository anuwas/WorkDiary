/**
 * 
 */
package com.org.pack.wd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.org.pack.wd.entity.Refinements;
import com.org.pack.wd.repository.RefinementsRepository;

/**
 * @author Anupam Biswas
 * 2021-10-23 19:21:44.211
 */
@Service
public class RefinementService {
	
	@Autowired
	RefinementsRepository refinementsRepository;
	
	public List<Refinements> getRefinementsByApplication(String applicationName,int pageNumber){
		Pageable pageWithElement = PageRequest.of(pageNumber, 50);
		return refinementsRepository.findAllByApplicationNameOrderByRefinementDateDesc(applicationName, pageWithElement);
	}

}
