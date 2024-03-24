package com.org.pack.wd.tickting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.tickting.entity.Tickets;

@Repository
public interface TicketsRepository extends PagingAndSortingRepository<Tickets,Long>{
	Page<Tickets> findByTicketStatusContainingIgnoreCase(String keyword, Pageable pageable);
	
}
