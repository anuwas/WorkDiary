/**
 *
 */
package com.org.pack.wd.skillup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.tickting.entity.Tickets;

/**
 * @author ambigo
 * 06-Mar-2025 1:37:43 am
 */
@Controller
public class SkillupController {
	
	@Autowired
	WordingCriteriaHelper wordingCriteriaHelper;

	@GetMapping("/list-all-wording")
	  public String getAll(
			  Model model, 
			  @RequestParam(required = false) String keyword,
			  @RequestParam(defaultValue = "1") int page,
			  @RequestParam(value = "engWord", required = false) String engWord,
			  @RequestParam(value = "benWord", required = false) String benWord,
			  @RequestParam(value = "engSynoname", required = false) String engSynoname,
			  @RequestParam(value = "benSynoname", required = false) String benSynoname,
			  @RequestParam(value = "memorised", required = false) String memorised,
			  @RequestParam(value = "status", required = false) String status)	
	{
		int size = 50;
	    try {
	      List<Wording> wordingList = new ArrayList<Wording>();
	      Pageable paging = PageRequest.of(page - 1, size,Sort.by("seqOrder").descending());

	      Page<Wording> pageTuts;
	     
	        //pageTuts = ticketsRepository.findByTicketStatusContainingIgnoreCase("Active", paging);
	    	  pageTuts = wordingCriteriaHelper.retrivePageWordingBySearchandSort(engWord,benWord, engSynoname, benSynoname,memorised,status,paging);
	        
	      
	      model.addAttribute("engWord", engWord);
	      model.addAttribute("benWord", benWord);
	      model.addAttribute("engSynoname", engSynoname);
	      model.addAttribute("benSynoname", benSynoname);
	      model.addAttribute("memorised", memorised);
	      model.addAttribute("status", status);

	      wordingList = pageTuts.getContent();

	      model.addAttribute("allwordingList", wordingList);
	      model.addAttribute("currentPage", pageTuts.getNumber() + 1);
	      model.addAttribute("totalItems", pageTuts.getTotalElements());
	      model.addAttribute("totalPages", pageTuts.getTotalPages());
	      model.addAttribute("pageSize", size);
	      
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	    }
	    
	    List<String> statusList = Arrays.asList("Active","Inactive");
	    List<String> memorisedList = Arrays.asList("Yes","No");
	      
	    model.addAttribute("statusList", statusList);
	    model.addAttribute("memorisedList", memorisedList);

	    return "skillup/list-all-wording";
	  }
}
