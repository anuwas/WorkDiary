package com.org.pack.wd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.dto.ActivityYearMonth;
import com.org.pack.wd.entity.ServiceActivity;

@Repository
public interface ServiceActivityRepository extends PagingAndSortingRepository<ServiceActivity,Long>{
	List<ServiceActivity> findAllByServiceYearNameAndServiceMonthNameOrderByServiceWeekDesc(String serviceYearName, String serviceMonthName);
	
	/*
	@Query("select serviceYearName,serviceMonthName from ServiceActivity group by serviceYearName,serviceMonthName")
	List<Object[]> findAllServiceActvityWithMonthYear();
	*/
	
	@Query("select new com.org.pack.wd.dto.ActivityYearMonth(serviceYearName,serviceMonthName) from ServiceActivity group by serviceYearName,serviceMonthName,serviceMonth,serviceMonth order by serviceYear DESC,serviceMonth DESC")
	List<ActivityYearMonth> findAllServiceActvityWithMonthYear();

	
	//List<ServiceActivity> findAllOrderByServiceWeekDesc();
}
