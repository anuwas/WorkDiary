package com.org.pack.wd.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	
	  public List<DiaryTask> getAllClosedTask(int pageNumber){ List<String>
	  closedTaskIn = Arrays.asList("Done","Closed"); Pageable pageWithElement =
	  PageRequest.of(pageNumber, 50); List<DiaryTask> allClosedTask =
	  diraryTaskRepository.findAllDiaryTaskByTaskStatusInOrderByTaskDateDesc(
	  closedTaskIn,pageWithElement); return allClosedTask; }
	 
	
	/*
	 * public Page<DiaryTask> getAllClosedTask(Pageable pageable){ List<String>
	 * closedTaskIn = Arrays.asList("Done","Closed"); int pageSize =
	 * pageable.getPageSize(); int currentPage = pageable.getPageNumber(); int
	 * startItem = currentPage * pageSize; Pageable pageWithElement =
	 * PageRequest.of(currentPage, 2); List<DiaryTask> allClosedTask =
	 * diraryTaskRepository.findAllDiaryTaskByTaskStatusInOrderByTaskDateDesc(
	 * closedTaskIn,pageWithElement); List<DiaryTask> list; if (allClosedTask.size()
	 * < startItem) { list = Collections.emptyList(); } else { int toIndex =
	 * Math.min(startItem + pageSize, allClosedTask.size()); list =
	 * allClosedTask.subList(startItem, toIndex); } Page<DiaryTask>
	 * allClosedTaskPage = new PageImpl<DiaryTask>(list, PageRequest.of(currentPage,
	 * pageSize), allClosedTask.size()); return allClosedTaskPage; }
	 */
	
	
	
}
