package com.org.pack.wd.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
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
		//LocalDateTime myDateObj = LocalDateTime.now();
		List<String> closedTaskIn = Arrays.asList("Done","Closed");
		List<DiaryTask> allCurrentActiveTask = diraryTaskRepository.findAllDiaryTaskByTaskDateBeforeAndTaskStatusNotIn(date, closedTaskIn);
		return allCurrentActiveTask;
	}
	
	public List<DiaryTask> getAllClosedCurrentTask(){
		//Date date = new Date();  
		//LocalDateTime myDateObj = LocalDateTime.now();
		List<String> closedTaskIn = Arrays.asList("Done","Closed");
		//List<DiaryTask> allCurrentActiveTask = diraryTaskRepository.findAllDiaryTaskByModifiedDateAndTaskStatusIn(date, closedTaskIn);
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String curDate = dateFormat.format(date); 
		
		List<DiaryTask> allCurrentActiveTask = diraryTaskRepository.findAllCurrentClosedTask(curDate, closedTaskIn);
		return allCurrentActiveTask;
	}
	
	public List<DiaryTask> getAllUpComingTask(){
		Date date = new Date();  
		List<String> closedTaskIn = Arrays.asList("Done","Closed");
		List<DiaryTask> allUpComingTask = diraryTaskRepository.findAllDiaryTaskByTaskDateAfterAndTaskStatusNotInOrderByTaskDateAsc(date, closedTaskIn);
		return allUpComingTask;
	}
	
	
	
}
