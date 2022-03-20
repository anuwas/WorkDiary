package com.org.pack.wd.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.org.pack.wd.entity.BookMark;
import com.org.pack.wd.repository.BookMarkRepository;

@Service
public class BookMarkService {
	
	@Autowired
	BookMarkRepository bookMarkRepository;
	
	public List<BookMark> getAllActiveBookMark(int pageNumber){
		List<String>  activeTask = Arrays.asList("Active"); 
		Pageable pageWithElement =  PageRequest.of(pageNumber, 50); 
		return bookMarkRepository.findAllBookMarkByBookMarkActiveInOrderByCreatedDateDesc(activeTask, pageWithElement);
		
	}

}
