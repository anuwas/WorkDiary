package com.org.pack.wd.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.application.repository.ApplicationsRepository;
import com.org.pack.wd.team.entiry.TeamMember;

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
	
	@GetMapping("/application-edit/{applicationid}")
	public String editApplicaiton(Model model,@PathVariable long applicationid) {
		Optional<Applications> currentApplicaiton = applicationsRepository.findById(applicationid);
		model.addAttribute("application", currentApplicaiton.get());
		model.addAttribute("applicationid", applicationid);
		
		return "applications/applications-edit-form";
	}
	
	@PostMapping("/application-update/{appid}")
	public String updateTeamMember(Applications applications,Model model,@PathVariable long appid) {
		applications.setId(appid);
		applicationsRepository.save(applications);
		
		return "redirect:/application-edit/"+appid;
	}

}
