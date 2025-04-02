/**
 *
 */
package com.org.pack.wd.skillup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.entity.DiaryTask;
import com.org.pack.wd.tickting.entity.TicketWorkAudit;
import com.org.pack.wd.tickting.entity.Tickets;

/**
 * @author ambigo
 * 06-Mar-2025 1:37:43 am
 */
@Controller
public class SkillupController {
	
	@Autowired
	WordingCriteriaHelper wordingCriteriaHelper;
	
	@Autowired
	WordingRepository wordingRepository;

	@GetMapping("/list-all-wording")
	  public String getAll(
			  Model model, 
			  @RequestParam(required = false) String keyword,
			  @RequestParam(defaultValue = "1") int page,
			  @RequestParam(value = "engWord", required = false,defaultValue = "") String engWord,
			  @RequestParam(value = "benWord", required = false,defaultValue = "") String benWord,
			  @RequestParam(value = "engSynoname", required = false,defaultValue = "") String engSynoname,
			  @RequestParam(value = "benSynoname", required = false,defaultValue = "") String benSynoname,
			  @RequestParam(value = "benSoudingEng", required = false,defaultValue = "") String benSoudingEng,
			  @RequestParam(value = "memorised", required = false,defaultValue = "") String memorised,
			  @RequestParam(value = "status", required = false,defaultValue = "") String status)	
	{
		int size = 50;
	    try {
	      List<Wording> wordingList = new ArrayList<Wording>();
	      Pageable paging = PageRequest.of(page - 1, size,Sort.by("seqOrder").descending());

	      Page<Wording> pageTuts;
	     
	        //pageTuts = ticketsRepository.findByTicketStatusContainingIgnoreCase("Active", paging);
	    	  pageTuts = wordingCriteriaHelper.retrivePageWordingBySearchandSort(engWord,benWord, engSynoname, benSynoname,benSoudingEng,memorised,status,paging);
	        
	      
	      model.addAttribute("engWord", engWord);
	      model.addAttribute("benWord", benWord);
	      model.addAttribute("engSynoname", engSynoname);
	      model.addAttribute("benSynoname", benSynoname);
	      model.addAttribute("benSoudingEng", benSoudingEng);
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
	    
	    Wording wordingOj = new Wording();
	    model.addAttribute("wordingOj", wordingOj);
	    return "skillup/list-all-wording";
	  }
	
	@PostMapping("/savewording")
	public String saveWording(Wording wording,Model model) {
		try {
			wordingRepository.save(wording);
			return "redirect:/list-all-wording";
		} catch (Exception e) {
			return "skillup/list-all-wording";
		}
	}
	
	@GetMapping("/deletewording/{id}")
	public String createTicketWorkerAudit(Model model,@PathVariable long id) {
		try {
			wordingRepository.deleteById(id);
			return "redirect:/list-all-wording";
		} catch (Exception e) {
			return "skillup/list-all-wording";
		}
	}
	
	@GetMapping("/edit-form-wording/{id}")
	public String eitFormWording(Model model,@PathVariable long id) {
		try {
			Optional<Wording> wordingOptional = wordingRepository.findById(id);
			Wording wordingObj = wordingOptional.get();
			model.addAttribute("wording", wordingObj);
			model.addAttribute("wordingId", id);
			List<String> statusList = Arrays.asList("Active","Inactive");
		    List<String> memorisedList = Arrays.asList("Yes","No");
		      
		    model.addAttribute("statusList", statusList);
		    model.addAttribute("memorisedList", memorisedList);
			return "skillup/wording-edit-form";
		} catch (Exception e) {
			return "skillup/list-all-wording";
		}
	}
	
	@PostMapping("/update-wording/{id}")
	public String updateWording(Wording wording,Model model,@PathVariable long id) {
		try {
			wording.setWordingId(id);
			wordingRepository.save(wording);
			return "redirect:/list-all-wording";
		} catch (Exception e) {
			return "skillup/list-all-wording";
		}
	}
	
	
	@GetMapping("/word-by-category")
	 public String getAll(Model model, 
			 @RequestParam(defaultValue = "1") int page,
			 @RequestParam(value = "type", required = false) String type) {
		int size = 1;
		Page<Wording> pageWording = null;
	    try {
	       
	      Pageable paging = PageRequest.of(page - 1, size,Sort.by("seqOrder").descending());
	      
	      switch(type) {
	      	case "nonmemorised":
	      		pageWording = wordingRepository.findAllByMemorised("No", paging);
	      		break;
	      	
	      	case "memorised":
	      		pageWording = wordingRepository.findAllByMemorised("Yes", paging);
	      		break;
	      }
	      
	       
	       List<Wording> allwordingList = pageWording.getContent();
	       
	      model.addAttribute("type", type);  
	      model.addAttribute("allwordingList", allwordingList);
	      model.addAttribute("currentPage", pageWording.getNumber() + 1);
	      model.addAttribute("totalItems", pageWording.getTotalElements());
	      model.addAttribute("totalPages", pageWording.getTotalPages());
	      model.addAttribute("pageSize", size);
	    }catch (Exception e) {
		      model.addAttribute("message", e.getMessage());
		    }
		return "skillup/word-by-category";
	}
	
	
}
