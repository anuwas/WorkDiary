/**
 *
 */
package com.org.pack.wd.skillup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.tickting.entity.Tickets;

/**
 * @author ambigo
 * 06-Mar-2025 1:36:03 am
 */
@Repository
public interface WordingRepository extends JpaRepository<Wording,Long>{
	Page<Wording> findAllByMemorised(String memorised, Pageable pageable);
}
