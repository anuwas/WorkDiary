package com.org.pack.wd.team.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.team.entiry.TeamMember;
import com.org.pack.wd.team.entiry.TeamMemberLeave;

@Repository
public interface TeamMemberLeaveRepository extends JpaRepository<TeamMemberLeave,Long>{
	List<TeamMemberLeave> findAllByTeamMemberAndLeaveDateBetweenOrderByLeaveDateDesc(TeamMember teamMember,Date startDate,Date endDate);
	
	
	List<TeamMemberLeave> findAllByLeaveDate(Date leaveDate);
	List<TeamMemberLeave> findAllByLeaveDateGreaterThanOrderByLeaveDateAsc(Date leaveDate);
	
}
