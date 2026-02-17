/**
 * 
 */
package com.org.pack.wd.scrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.scrum.entity.Sprint;

/**
 * @author ambigo
 * 15-Feb-2026 11:05:59 pm
 */
@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
	
	List<Sprint> findAllByOrderByCreatedDateDesc();
	
	// list of last 10 sprint in descending order
	List<Sprint> findTop10ByOrderByCreatedDateDesc();

}
