package com.org.pack.wd.tickting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.tickting.entity.Tickettype;

@Repository
public interface TickettypeRepository extends JpaRepository<Tickettype, Long>{

}
