/**
 * 
 */
package com.org.pack.wd.scrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pack.wd.scrum.entity.Sprint;
import com.org.pack.wd.scrum.repository.SprintRepository;

/**
 * @author ambigo
 * 15-Feb-2026 10:57:02 pm
 */
@Service
public class ScrumService {
	
	
	@Autowired
	SprintRepository sprintRepository;
	
	public List<Sprint> findAllByOrderByCreatedDateDesc() {
		return sprintRepository.findAllByOrderByCreatedDateDesc();
	}

}
