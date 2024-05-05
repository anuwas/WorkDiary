package com.org.pack.wd.team.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.team.entiry.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember,Long>{
	
	List<TeamMember> findAllByStatus(String status);

}
