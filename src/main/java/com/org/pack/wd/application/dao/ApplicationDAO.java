package com.org.pack.wd.application.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.application.entity.Applications;


@Repository
public class ApplicationDAO {

	@Autowired
	EntityManager em;
	
	public List<Applications> getApplicationsByCriteria(String appStatusSrcReq,
			String technolgySrcReq, String businessStrmSrcReq, String seriesSrcReq, String interfacesSrcReq){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Applications> cq = cb.createQuery(Applications.class);
		
		Root<Applications> teamRoot = cq.from(Applications.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (!appStatusSrcReq.equals("All")) {
		        predicates.add(cb.equal(teamRoot.get("appStatus"), appStatusSrcReq));
			}
		 if (!technolgySrcReq.equals("All")) {
		        predicates.add(cb.like(teamRoot.get("technology"), "%" + technolgySrcReq + "%"));
		    }
		 if (!businessStrmSrcReq.equals("All")) {
		        predicates.add(cb.like(teamRoot.get("businessStream"), "%" + businessStrmSrcReq + "%"));
		    }
		 if (!seriesSrcReq.equals("All")) {
		        predicates.add(cb.like(teamRoot.get("series"), "%" + seriesSrcReq + "%"));
		    }
		 if (!interfacesSrcReq.equals("All")) {
		        predicates.add(cb.like(teamRoot.get("interfaces"), "%" + interfacesSrcReq + "%"));
		    }
		 
		 cq.where(predicates.toArray(new Predicate[0]));
			
		 return em.createQuery(cq).getResultList();
	}
	
}
