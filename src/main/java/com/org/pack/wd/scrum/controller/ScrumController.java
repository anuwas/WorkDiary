package com.org.pack.wd.scrum.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.dto.ActivityYearMonth;
import com.org.pack.wd.entity.ServiceActivity;
import com.org.pack.wd.scrum.entity.Sprint;
import com.org.pack.wd.scrum.repository.SprintRepository;
import com.org.pack.wd.scrum.service.ScrumService;
import com.org.pack.wd.util.ConstantProperties;

@Controller
public class ScrumController {
	
	@Autowired
	ScrumService scrumService;
	
	@Autowired
	SprintRepository sprintRepository;
	
	
	 @PostMapping("/sprint-activity-update/{id}")
	    public String updateSprintActivity(Model model,@PathVariable long id,@ModelAttribute("sprintOject") Sprint sprintOject) {
		 
		 sprintOject.setId(id);
		 sprintRepository.save(sprintOject);
		 return "redirect:/view-sprint-activity/"+id;
	    }
	
	@GetMapping("/view-sprint-activity/{id}")
	public String viewSprintActivity(Model model,@PathVariable Long id) {
		Optional<Sprint> spring = sprintRepository.findById(id);
		List<String> statulsList = Arrays.asList("Active", "Completed", "Canclled");
		model.addAttribute("statulsListDDL", statulsList);
		model.addAttribute("sprintOject", spring.get());
		return "scrum/view-edit-sprint-activity";
	}
	
	@PostMapping("/createnewsprint")
	public String createNewServiceActivityFrom(Sprint sprint,Model model) {
		try {
			sprintRepository.save(sprint);
			return "redirect:/view-sprint-activity/"+sprint.getId();
		} catch (Exception e) {
			return "task-form";
		}
	}
	
	@GetMapping("/show-detail-sprint_list")
	public String showAllShowDetail(Model model) {
		List<Sprint> sprintList = sprintRepository.findTop10ByOrderByCreatedDateDesc();
		model.addAttribute("sprintList", sprintList);
		return "scrum/show-detail-sprint_list";
	}
	
	@GetMapping("/all-sprints")
	public String allServiceActivities(Model model) {
		List<Sprint> sprintList = scrumService.findAllByOrderByCreatedDateDesc();
		model.addAttribute("sprintList", sprintList);
		Sprint sprintObject = new Sprint();
		model.addAttribute("sprintObject", sprintObject);
		return "scrum/all-sprints";
	}
	
}
