package com.org.pack.wd.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.repository.DiraryTaskRepository;
import com.org.pack.wd.service.DiaryTaskService;
import com.org.pack.wd.util.ConstantProperties;

@Controller
public class TaskController {
	
	 @Autowired
	 DiaryTaskService diaryTaskService;
	 
	 @Autowired 
	 DiraryTaskRepository diraryTaskRepository;

	@GetMapping("/current-task")
	public String currentTask(Model model) {
		List<DiaryTask> activeTaskList = diaryTaskService.getAllActiveCurrentTask();
		List<DiaryTask> closedTaskList = diaryTaskService.getAllClosedCurrentTask();
		List<DiaryTask> backlogTaskList = diaryTaskService.getAllBacklogPendingTask();
		model.addAttribute("currentActiveTask", activeTaskList);
		model.addAttribute("backlogActiveTask", backlogTaskList);
		model.addAttribute("currentClosedTask", closedTaskList);
		return "current-task";
	}
	
	@GetMapping("/upcoming-task")
	public String upComingTask(Model model) {
		List<DiaryTask> upComingTaskList = diaryTaskService.getAllUpComingTask();
		model.addAttribute("upComingTaskList", upComingTaskList);
		return "upcoming-task";
	}
	
	@GetMapping("/task-form")
	public String taskForm(Model model) {
		DiaryTask diaryTaskObject = new DiaryTask();
        model.addAttribute("diaryTask", diaryTaskObject);
        List<String> listTaskStatus = Arrays.asList("Pending", "Inprogress","OnHold","Closed");
        Map<Integer,String> taskPriorityMap = ConstantProperties.TASK_PRIORITY_MAP;
        model.addAttribute("listTaskStatus", listTaskStatus);
        model.addAttribute("taskPriorityMap", taskPriorityMap);
		return "task-form";
	}
	
	@PostMapping("/savetask")
	public String createTutorial(DiaryTask diaryTask,Model model) {
		try {
			diraryTaskRepository.save(diaryTask);
			return "redirect:/current-task";
		} catch (Exception e) {
			return "task-form";
		}
	}
	
	 @GetMapping("/task-edit/{id}")
	    public String taskManagerEdit(Model model,@PathVariable long id) {
		 Optional<DiaryTask> diaryTaskObject = null;
		 diaryTaskObject = diraryTaskRepository.findById(id);
	     model.addAttribute("diaryTask", diaryTaskObject.get());
	        
	     List<String> listTaskStatus = Arrays.asList("Pending", "Inprogress","OnHold","Closed");
	     Map<Integer,String> taskPriorityMap = ConstantProperties.TASK_PRIORITY_MAP;
	     model.addAttribute("taskPriorityMap", taskPriorityMap);
	     model.addAttribute("listTaskStatus", listTaskStatus);
	        
	     return "task-edit"; //view
	    }
	 
	 @PostMapping("/task-update/{id}")
	    public String taskManagerUpdate(Model model,@PathVariable long id,@ModelAttribute("diaryTask") DiaryTask diaryTask) {
		 
		 diaryTask.setId(id);
		 diraryTaskRepository.save(diaryTask);
		 return "redirect:/current-task";
	    }
	 
		
		  @GetMapping("/all-closed-task/{page}") 
		  public String allClosedTask(Model  model,@PathVariable int page) { 
			  List<DiaryTask> allClosedTaskList =  diaryTaskService.getAllClosedTask(page);
		  model.addAttribute("allClosedTaskList", allClosedTaskList);
		  model.addAttribute("page", page); return "closed-task"; }
		 
	 
		/*
		 * @GetMapping("/all-closed-task") public String allClosedTask(Model
		 * model,@RequestParam("page") Optional<Integer> page,
		 * 
		 * @RequestParam("size") Optional<Integer> size) { int currentPage =
		 * page.orElse(1); int pageSize = size.orElse(2);
		 * 
		 * Page<DiaryTask> closedTaskPage =
		 * diaryTaskService.getAllClosedTask(PageRequest.of(currentPage - 1, pageSize));
		 * model.addAttribute("closedTaskPage", closedTaskPage);
		 * 
		 * int totalPages = closedTaskPage.getTotalPages(); if (totalPages > 0) {
		 * List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages) .boxed()
		 * .collect(Collectors.toList()); model.addAttribute("pageNumbers",
		 * pageNumbers); }
		 * 
		 * 
		 * return "closed-task-page"; }
		 */
}
