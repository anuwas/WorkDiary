package com.org.pack.wd.team.repository;

import java.util.List;

import com.org.pack.wd.team.entiry.TeamMember;

public interface TeamMemberCriterias {
	
	List<TeamMember> findAllBySearchCriteria(String status,String skillset,String organizationDesignation,String organizationDepartment);

}
