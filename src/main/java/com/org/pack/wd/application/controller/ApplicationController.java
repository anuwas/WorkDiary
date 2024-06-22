package com.org.pack.wd.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.application.repository.ApplicationsRepository;

@Controller
public class ApplicationController {
	
	@Autowired
	ApplicationsRepository applicationsRepository;
	
	@GetMapping("/all-applications")
	public String allServiceActivities(Model model) {
	
		List<Applications> allApplications = applicationsRepository.findAll();
		model.addAttribute("allApplications", allApplications);
		return "applications/all-applications";
	}

}
