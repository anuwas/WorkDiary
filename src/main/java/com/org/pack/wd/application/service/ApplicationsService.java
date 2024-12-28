package com.org.pack.wd.application.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pack.wd.application.dao.ApplicationDAO;
import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.application.repository.ApplicationsRepository;

@Service
public class ApplicationsService {
	
	@Autowired
	ApplicationsRepository applicationsRepository;
	
	
	
	public List<String> getListofInterfaces(){
		List<String> applicationInterfaceList = applicationsRepository.findAll().stream()
				.filter(p -> p.getInterfaces() != null)
				.filter(q->!q.getInterfaces().equals(""))
				.map(s->s.getInterfaces().split(","))
				.flatMap(Arrays::stream).distinct().sorted()
				.collect(Collectors.toList());
				
		return applicationInterfaceList;
	}
	
	public List<String> getTechnologyLIst(){
		List<String> teamMemberTechnologyList = applicationsRepository.findAll().stream()
				.filter(s->s.getTechnology()!=null)
				.filter(p->!p.getTechnology().equals(""))
				.map(q->q.getTechnology().split(","))
				.flatMap(Arrays::stream)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		return teamMemberTechnologyList;
	}
	
	
}
