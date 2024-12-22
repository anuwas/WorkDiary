package com.org.pack.wd.team.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.team.entiry.TeamMember;
import com.org.pack.wd.team.repository.TeamMemberCriterias;

@Repository
public class TeamMemberCriteriasImpl implements TeamMemberCriterias{

	@Autowired
	EntityManager em;
	
	@Override
	public List<TeamMember> findAllBySearchCriteria(String status,String skillset,String organizationDesignation,String organizationDepartment) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TeamMember> cq = cb.createQuery(TeamMember.class);
		
		Root<TeamMember> teamRoot = cq.from(TeamMember.class);
		List<Predicate> predicates = new ArrayList<>();
		
		 if (!status.equals("All")) {
		        predicates.add(cb.equal(teamRoot.get("status"), status));
			} /*else 
					 * { predicates.add(cb.notEqual(teamRoot.get("status"), status)); }
					 */
		 
		 if (!skillset.equals("All")) {
		        predicates.add(cb.like(teamRoot.get("skillset"), "%" + skillset + "%"));
		    }
		 if (!organizationDesignation.equals("All")) {
		        predicates.add(cb.equal(teamRoot.get("organizationDesignation"), organizationDesignation));
			}/*
		 if (!organizationDesignation.equals("All")) {
		        predicates.add(cb.equal(teamRoot.get("organizationDepartment"), organizationDepartment));
			}*/
		 if (!organizationDepartment.equals("All")) {
		        predicates.add(cb.like(teamRoot.get("organizationDepartment"), "%" + organizationDepartment + "%"));
		    }
		 
		 cq.where(predicates.toArray(new Predicate[0]));
		
		 return em.createQuery(cq).getResultList();
	}

}
