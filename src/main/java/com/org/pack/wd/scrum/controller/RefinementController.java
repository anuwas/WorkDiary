/**
 * 
 */
package com.org.pack.wd.scrum.controller;


import com.org.pack.wd.scrum.entity.Refinements;
import com.org.pack.wd.scrum.repository.RefinementsRepository;
import com.org.pack.wd.scrum.service.RefinementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


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
	public String taskManagerEdit(Model model,@PathVariable String applicationName,@PathVariable int page) {
		List<Refinements> refinementsList = refinementService.getRefinementsByApplication(applicationName, page);
		Refinements refinementsObject = new Refinements();
		model.addAttribute("refinements", refinementsObject);
		model.addAttribute("refinementsList", refinementsList);
		model.addAttribute("page",page);
		return "scrum/refinement";
	}
	
	@PostMapping("/saverefinement")
	public String createTutorial(Refinements refinements,Model model) {
		try {
			//String currentApplication = (String) session.getAttribute("currentApplicationName");
			//refinements.setApplicationName("EPR-Results");
			refinementsRepository.save(refinements);
			return "redirect:/refinement-edit/"+refinements.getId();
		} catch (Exception e) {
			return "task-form";
		}
	}
	
	@GetMapping("/refinement-edit/{id}")
	public String editRefinement(Model model,@PathVariable long id) {
		Optional<Refinements> refinementsObject = null;
		refinementsObject = refinementsRepository.findById(id);
	     model.addAttribute("refinementsObject", refinementsObject.get());
	     return "scrum/refinement-edit";
	}
	
	 @PostMapping("/refinement-update/{id}")
	    public String taskManagerUpdate(Model model,@PathVariable long id,@ModelAttribute("refinementsObject") Refinements refinementsObject) {
		 
		 //refinementsObject.setId(id);
		 //refinementsObject.setApplicationName(refinementsObject.getApplicationName());
		 refinementsRepository.save(refinementsObject);
		 return "redirect:/refinement-edit/"+id;
	    }

	@GetMapping("/refinements-dashboard")
	public String refinementDashboardService(Model model) {
		List<Refinements> refinementsList = refinementService.findAllByOrderByCreatedDateDesc();
		model.addAttribute("refinementsList", refinementsList);
		Refinements refinementsObject = new Refinements();
		model.addAttribute("refinementsObject", refinementsObject);
		return "scrum/refinements-dashboard";
	}


	
}
