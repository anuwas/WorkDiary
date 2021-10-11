package com.org.pack.wd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.entity.DiaryTask;

@Repository
public interface DiraryTaskRepository extends JpaRepository<DiaryTask,Long>{
	public List<DiaryTask> findAllDiaryTaskByTaskDateAndTaskStatusNotIn(Date taskDate,List<String> taskStatus);
	public List<DiaryTask> findAllDiaryTaskByTaskDateBeforeAndTaskStatusNotIn(Date taskDate,List<String> taskStatus);
}
