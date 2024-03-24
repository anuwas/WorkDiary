package com.org.pack.wd.tickting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.tickting.entity.TicketWorkAudit;
import com.org.pack.wd.tickting.entity.Tickets;
import com.org.pack.wd.tickting.entity.Ticketstatus;

@Repository
public interface TicketWorkAuditRepository extends JpaRepository<TicketWorkAudit,Long>{

	List<TicketWorkAudit> findAllByTickets(Tickets tickets);
	
}
