package com.org.pack.wd.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.repository.DiraryTaskRepository;
import com.org.pack.wd.service.DiaryTaskService;

@Controller
public class WelcomeController {

	 @Value("${welcome.message}")
	 private String message;
	 
	 @Autowired
	 DiaryTaskService diaryTaskService;
	 
		
	    @GetMapping("/")
	    public String main(Model model) {
	        model.addAttribute("message", message);
	        List<DiaryTask> activeTaskList = diaryTaskService.getAllActiveCurrentTask();
	        System.out.println(activeTaskList);
	        model.addAttribute("currentActiveTask", activeTaskList);
	        
	        DiaryTask diaryTaskObject = new DiaryTask();
	        model.addAttribute("diaryTask", diaryTaskObject);
	        
	        List<String> listTaskStatus = Arrays.asList("Pending", "Inprogress","OnHold","Closed");
	        List<String> taskPriorityList = Arrays.asList("Medium", "High","Low");
	        model.addAttribute("listTaskStatus", listTaskStatus);
	        model.addAttribute("taskPriorityList", taskPriorityList);
	        
	        return "index"; //view
	    }
	    
	    @GetMapping("/tables")
	    public String tableView(Model model) {
	    	return "basic-table";
	    }
	    
	    @GetMapping("/forms")
	    public String formsView(Model model) {
	    	return "basic_elements";
	    }
	    
	    @GetMapping("/hello")
	    public String mainWithParam(
	            @RequestParam(name = "name", required = false, defaultValue = "") 
				String name, Model model) {

	        model.addAttribute("message", name);

	        return "activetasklist"; //view
	    }
	    
}
