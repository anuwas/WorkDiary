/**
 * 
 */
package com.org.pack.wd.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.entity.Refinements;
import com.org.pack.wd.repository.RefinementsRepository;
import com.org.pack.wd.service.RefinementService;



/**
 * @author Anupam Biswas
 * 2021-10-23 19:52:42.650
 */
@Controller
public class RefinementController {
	
	@Autowired
	RefinementService refinementService;
	
	@Autowired 
	RefinementsRepository refinementsRepository;
	
	@GetMapping("/refinement/{applicationName}/{page}")
	public String taskManagerEdit(Model model,HttpSession session,@PathVariable String applicationName,@PathVariable int page) {
		List<Refinements> refinementsList = refinementService.getRefinementsByApplication(applicationName, page);
		Refinements refinementsObject = new Refinements();
		model.addAttribute("refinements", refinementsObject);
		model.addAttribute("refinementsList", refinementsList);
		session.setAttribute("currentApplicationName", applicationName);
		model.addAttribute("page",page);
		return "refinement";
	}
	
	@PostMapping("/saverefinement")
	public String createTutorial(Refinements refinements,HttpSession session,Model model) {
		try {
			String currentApplication = (String) session.getAttribute("currentApplicationName");
			refinements.setApplicationName(currentApplication);
			Date date = new Date();
			refinements.setRefinementDate(date);
			refinementsRepository.save(refinements);
			return "redirect:/refinement/"+refinements.getApplicationName()+"/0";
		} catch (Exception e) {
			return "task-form";
		}
	}
	
}
