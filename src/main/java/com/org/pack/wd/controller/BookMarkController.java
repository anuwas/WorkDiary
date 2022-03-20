package com.org.pack.wd.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.entity.BookMark;
import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.repository.BookMarkRepository;
import com.org.pack.wd.service.BookMarkService;
import com.org.pack.wd.util.ConstantProperties;

@Controller
public class BookMarkController {
	
	@Autowired
	BookMarkService bookMarkService;
	
	@Autowired
	BookMarkRepository bookMarkRepository;
	
	 @GetMapping("/get-all-active-bookmarks/{page}") 
	  public String allClosedTask(Model  model,@PathVariable int page) { 
		  List<BookMark> allAvtiveBookMarkList =  bookMarkService.getAllActiveBookMark(page);
		  model.addAttribute("allAvtiveBookMarkList", allAvtiveBookMarkList);
		  model.addAttribute("page", page); 
		  return "bookmark/book-mark-list"; 
	}
	 
		@GetMapping("/create-new-bookmark")
		public String taskForm(Model model) {
			BookMark diaryTaskObject = new BookMark();
	        model.addAttribute("bookMark", diaryTaskObject);
			return "bookmark/bookmark-form";
		}
		
		@PostMapping("/save-bookmark")
		public String createBookmark(BookMark bookMark,Model model) {
			try {
				bookMark.setBookMarkActive("Active");
				bookMarkRepository.save(bookMark);
				return "redirect:/get-all-active-bookmarks/0";
			} catch (Exception e) {
				return "bookmark/bookmark-form";
			}
		}
		
		@GetMapping("/edit-bookmark/{id}")
	    public String taskManagerEdit(Model model,@PathVariable long id) {
		 Optional<BookMark> diaryTaskObject = null;
		 diaryTaskObject = bookMarkRepository.findById(id);
	     model.addAttribute("bookMark", diaryTaskObject.get());    
	        
	     return "bookmark/bookmark-form-update"; //view
	    }
		
		@PostMapping("/bookmark-update/{id}")
	    public String taskManagerUpdate(Model model,@PathVariable long id,@ModelAttribute("bookMark") BookMark bookMark) {
		 
			bookMark.setId(id);
			bookMarkRepository.save(bookMark);
		 return "redirect:/edit-bookmark/"+id;
	    }
	
	

}
