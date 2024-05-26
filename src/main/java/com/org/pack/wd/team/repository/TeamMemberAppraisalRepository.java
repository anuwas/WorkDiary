package com.org.pack.wd.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.team.entiry.TeamMember;
import com.org.pack.wd.team.entiry.TeamMemberAppraisal;

@Repository
public interface TeamMemberAppraisalRepository extends JpaRepository<TeamMemberAppraisal,Long>{
	TeamMemberAppraisal findByTeamMemberAndFinancialYear(TeamMember teamMember, String finalcialYear);
}
