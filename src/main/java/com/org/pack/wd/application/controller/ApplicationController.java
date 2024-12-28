package com.org.pack.wd.application.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.application.dao.ApplicationDAO;
import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.application.repository.ApplicationsRepository;
import com.org.pack.wd.application.service.ApplicationsService;

@Controller
public class ApplicationController {
	
	@Autowired
	ApplicationsRepository applicationsRepository;
	
	@Autowired
	ApplicationsService applicationsService;
	
	@Autowired
	ApplicationDAO applicationDAO;
	
	@GetMapping("/all-applications")
	public String allServiceActivities(Model model,
			@RequestParam(value = "appStatus",defaultValue = "Active",required = false) String appStatusSrcReq,
			@RequestParam(value = "technology",defaultValue = "All", required = false) String technolgySrcReq,
			@RequestParam(value = "businessStream",defaultValue = "All", required = false) String businessStrmSrcReq,
			@RequestParam(value = "series",defaultValue = "All", required = false) String seriesSrcReq,
			@RequestParam(value = "interfaces",defaultValue = "All", required = false) String interfacesSrcReq) 
	{
	
		List<Applications> allApplications = applicationDAO.getApplicationsByCriteria(appStatusSrcReq, technolgySrcReq, businessStrmSrcReq, seriesSrcReq, interfacesSrcReq);
		model.addAttribute("allApplications", allApplications);
		
		List<String> appstatusList = Arrays.asList("Active","Inactive");
		model.addAttribute("appstatusList", appstatusList);
		List<String> technologyList = applicationsService.getTechnologyLIst();
		model.addAttribute("technologyList", technologyList);
		List<String> businessStreamList = Arrays.asList("CIE","OCR");
		model.addAttribute("businessStreamList", businessStreamList);
		List<String> interfaceList = applicationsService.getListofInterfaces();
		model.addAttribute("interfaceList", interfaceList);
		List<String> seriesList = Arrays.asList("Summer","Winter","Autum");
		model.addAttribute("seriesList", seriesList);
		
		model.addAttribute("appStatus", appStatusSrcReq);
		model.addAttribute("technology", technolgySrcReq);
		model.addAttribute("businessStream", businessStrmSrcReq);
		model.addAttribute("series", seriesSrcReq);
		model.addAttribute("interfaces", interfacesSrcReq);
		
		
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
