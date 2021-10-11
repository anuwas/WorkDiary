package com.org.pack.wd.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.repository.DiraryTaskRepository;
import com.org.pack.wd.service.DiaryTaskService;

@Controller
public class TaskController {
	
	 @Autowired
	 DiaryTaskService diaryTaskService;
	 
	 @Autowired 
	 DiraryTaskRepository diraryTaskRepository;

	@GetMapping("/current-task")
	public String currentTask(Model model) {
		List<DiaryTask> activeTaskList = diaryTaskService.getAllActiveCurrentTask();
		List<DiaryTask> backlogTaskList = diaryTaskService.getAllBacklogPendingTask();
		model.addAttribute("currentActiveTask", activeTaskList);
		model.addAttribute("backlogActiveTask", backlogTaskList);
		return "current-task";
	}
	
	@GetMapping("/task-form")
	public String taskForm(Model model) {
		DiaryTask diaryTaskObject = new DiaryTask();
        model.addAttribute("diaryTask", diaryTaskObject);
        List<String> listTaskStatus = Arrays.asList("Pending", "Inprogress","OnHold","Closed");
        List<String> taskPriorityList = Arrays.asList("Medium", "High","Low");
        model.addAttribute("listTaskStatus", listTaskStatus);
        model.addAttribute("taskPriorityList", taskPriorityList);
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
	     List<String> taskPriorityList = Arrays.asList("Medium", "High","Low");
	     model.addAttribute("listTaskStatus", listTaskStatus);
	     model.addAttribute("taskPriorityList", taskPriorityList);
	        
	     return "task-edit"; //view
	    }
	 
	 @PostMapping("/task-update/{id}")
	    public String taskManagerUpdate(Model model,@PathVariable long id,@ModelAttribute("diaryTask") DiaryTask diaryTask) {
		 
		 diaryTask.setId(id);
		 diraryTaskRepository.save(diaryTask);
		 return "redirect:/current-task";
	    }
}
