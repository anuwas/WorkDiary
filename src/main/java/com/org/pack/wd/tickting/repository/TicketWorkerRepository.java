package com.org.pack.wd.tickting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.tickting.entity.TicketWorker;

@Repository
public interface TicketWorkerRepository extends JpaRepository<TicketWorker, Long>{

}
