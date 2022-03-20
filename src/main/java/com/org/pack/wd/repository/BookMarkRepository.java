package com.org.pack.wd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.entity.BookMark;

@Repository
public interface BookMarkRepository extends PagingAndSortingRepository<BookMark,Long>{
	
	List<BookMark> findAllBookMarkByBookMarkActiveInOrderByCreatedDateDesc(List<String> taskStatus,Pageable pageable);

}
