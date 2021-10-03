package com.org.pack.wd.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.repository.DiraryTaskRepository;
import com.org.pack.wd.service.DiaryTaskService;

@Controller
//@RequestMapping("/api")
public class DiaryTaskController {

	@Autowired
	DiaryTaskService diaryTaskService;
	
	@Autowired 
	DiraryTaskRepository diraryTaskRepository;
	
	@PostMapping("/savetask")
	public String createTutorial(DiaryTask diaryTask,Model model) {
		try {
			diraryTaskRepository.save(diaryTask);
			return "redirect:/task-manager";
		} catch (Exception e) {
			return "task-manager";
		}
	}
	
	
	@GetMapping("/allDiaryTasks")
	public ResponseEntity<List<DiaryTask>> getAllTutorials() {
		try {
			List<DiaryTask> taskDiaryList = diraryTaskRepository.findAll();
			

			return new ResponseEntity<>(taskDiaryList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	 @GetMapping("/task-manager")
	    public String main(Model model) {
		 DiaryTask diaryTaskObject = new DiaryTask();
	        model.addAttribute("diaryTask", diaryTaskObject);
	        
	        List<String> listTaskStatus = Arrays.asList("Pending", "Inprogress","OnHold","Closed");
	        model.addAttribute("listTaskStatus", listTaskStatus);
	        
	        return "taskform"; //view
	    }
}
