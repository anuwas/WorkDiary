package com.org.pack.wd.tickting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.tickting.entity.Ticketstatus;

@Repository
public interface TicketstatusRepository extends JpaRepository<Ticketstatus,Long>{

}
