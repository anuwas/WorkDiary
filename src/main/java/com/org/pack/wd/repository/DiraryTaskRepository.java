package com.org.pack.wd.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.entity.DiaryTask;

@Repository
public interface DiraryTaskRepository extends PagingAndSortingRepository<DiaryTask,Long>{
	public List<DiaryTask> findAllDiaryTaskByTaskDateAndTaskStatusNotInOrderByTaskPriorityAsc(Date taskDate,List<String> taskStatus);
	
	@Query(value="SELECT * FROM DIARY_TASK t WHERE TRUNC(t.MODIFIED_DATE)= :taskDate AND t.TASK_STATUS IN :taskStatus",nativeQuery = true)
	public List<DiaryTask> findAllCurrentClosedTask(@Param("taskDate") String taskDate,@Param("taskStatus") Collection<String> taskStatus);
	//public List<DiaryTask> findAllDiaryTaskByTaskDateAndTaskStatusIn(Date taskDate,List<String> taskStatus);
	public List<DiaryTask> findAllDiaryTaskByTaskDateBeforeAndTaskStatusNotInOrderByTaskPriorityAsc(Date taskDate,List<String> taskStatus);
	public List<DiaryTask> findAllDiaryTaskByTaskDateAfterAndTaskStatusNotInOrderByTaskDateAsc(Date taskDate,List<String> taskStatus);
	public List<DiaryTask> findAllDiaryTaskByTaskStatusInOrderByTaskDateDesc(List<String> taskStatus,Pageable pageable);
}
