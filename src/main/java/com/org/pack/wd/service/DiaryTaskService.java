package com.org.pack.wd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.repository.DiraryTaskRepository;

@Service
public class DiaryTaskService {

	@Autowired
	DiraryTaskRepository diraryTaskRepository;
	
	public List<DiaryTask> getAllActiveCurrentTask(){
		Date date = new Date();  
		LocalDateTime myDateObj = LocalDateTime.now();
		List<String> closedTaskIn = Arrays.asList("Done","Closed");
		List<DiaryTask> allCurrentActiveTask = diraryTaskRepository.findAllDiaryTaskByTaskDateAndTaskStatusNotIn(date, closedTaskIn);
		return allCurrentActiveTask;
	}
	
	public List<DiaryTask> getAllBacklogPendingTask(){
		Date date = new Date();  
		LocalDateTime myDateObj = LocalDateTime.now();
		List<String> closedTaskIn = Arrays.asList("Done","Closed");
		List<DiaryTask> allCurrentActiveTask = diraryTaskRepository.findAllDiaryTaskByTaskDateBeforeAndTaskStatusNotIn(date, closedTaskIn);
		return allCurrentActiveTask;
	}
	
	public List<DiaryTask> getAllClosedCurrentTask(){
		Date date = new Date();  
		LocalDateTime myDateObj = LocalDateTime.now();
		List<String> closedTaskIn = Arrays.asList("Done","Closed");
		List<DiaryTask> allCurrentActiveTask = diraryTaskRepository.findAllDiaryTaskByTaskDateAndTaskStatusIn(date, closedTaskIn);
		return allCurrentActiveTask;
	}
	
	
	
}
