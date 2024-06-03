package com.org.pack.wd.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.dto.ActivityYearMonth;
import com.org.pack.wd.entity.ServiceActivity;
import com.org.pack.wd.repository.ServiceActivityRepository;
import com.org.pack.wd.util.ConstantProperties;

@Controller
public class ServiceActivityController {
	
	@Autowired
	ServiceActivityRepository serviceActivityRepository;
	
	@GetMapping("/service-activity/{yearName}/{monthName}")
	public String getActivityByYearMonth(Model model,HttpSession session,@PathVariable String yearName,@PathVariable String monthName) {
		List<ServiceActivity> serviceActivityList = serviceActivityRepository.findAllByServiceYearNameAndServiceMonthNameOrderByServiceWeekDesc(yearName, monthName);
		ServiceActivity serviceActivityObject = new ServiceActivity();
		model.addAttribute("serviceActivityObject", serviceActivityObject);
		model.addAttribute("serviceActivityList", serviceActivityList);
		session.setAttribute("yearName", yearName);
		session.setAttribute("monthName", monthName);
		return "serviceactivity/serviceactivity";
	}
	
	@PostMapping("/saveserviceactivity")
	public String createNewServiceActivity(ServiceActivity serviceActivity,HttpSession session,Model model) {
		try {
			String yearName = (String) session.getAttribute("yearName");
			String monthName = (String) session.getAttribute("monthName");
			int monthIndex = ConstantProperties.MONTH_NAME_INDEX_MAP.get(monthName);
			serviceActivity.setServiceYearName(yearName);
			serviceActivity.setServiceMonthName(monthName);
			serviceActivity.setServiceMonth(monthIndex);
			int serviceYear = Integer.parseInt((String) session.getAttribute("yearName")) ;
			serviceActivity.setServiceYear(serviceYear);
			serviceActivityRepository.save(serviceActivity);
			return "redirect:/service-activity/"+yearName+"/"+monthName;
		} catch (Exception e) {
			return "task-form";
		}
	}
	
	@PostMapping("/saveserviceactivity-from")
	public String createNewServiceActivityFrom(ServiceActivity serviceActivity,Model model) {
		try {
			//String yearName = (String) session.getAttribute("yearName");
			//String monthName = (String) session.getAttribute("monthName");
			int monthIndex = ConstantProperties.MONTH_NAME_INDEX_MAP.get(serviceActivity.getServiceMonthName());
			//serviceActivity.setServiceYearName(yearName);
			//serviceActivity.setServiceMonthName(monthName);
			serviceActivity.setServiceMonth(monthIndex);
			int serviceYear = Integer.parseInt((String) serviceActivity.getServiceYearName()) ;
			serviceActivity.setServiceYear(serviceYear);
			serviceActivityRepository.save(serviceActivity);
			return "redirect:/service-activity/"+serviceActivity.getServiceYearName()+"/"+serviceActivity.getServiceMonthName();
		} catch (Exception e) {
			return "task-form";
		}
	}
	
	@GetMapping("/service-activity-edit/{id}")
	public String editServiceActivity(Model model,@PathVariable long id) {
		Optional<ServiceActivity> serviceActivityObject = null;
		serviceActivityObject = serviceActivityRepository.findById(id);
	     model.addAttribute("serviceActivityObject", serviceActivityObject.get());
	     return "serviceactivity/serviceactivity-edit-form";
	}
	
	 @PostMapping("/service-activity-update/{id}")
	    public String taskManagerUpdate(Model model,@PathVariable long id,@ModelAttribute("serviceActivityObject") ServiceActivity serviceActivityObject) {
		 
		 serviceActivityObject.setId(id);
		 serviceActivityRepository.save(serviceActivityObject);
		 return "redirect:/service-activity/"+serviceActivityObject.getServiceYearName()+"/"+serviceActivityObject.getServiceMonthName();
	    }
	 
		@GetMapping("/all-service-activity")
		public String allServiceActivities(Model model) {
			List<ServiceActivity> serviceActivityList = (List<ServiceActivity>) serviceActivityRepository.findAll(Sort.by(Sort.Direction.DESC, "serviceWeek"));
			//Map<String,Map<String,List<ServiceActivity>>> groupedStudent =  serviceActivityList.stream().collect(Collectors.groupingBy(ServiceActivity::getServiceYearName,Collectors.groupingBy(ServiceActivity::getServiceMonthName)));
			List<ActivityYearMonth> listServiceActivityYearMonth = serviceActivityRepository.findAllServiceActvityWithMonthYear();
			model.addAttribute("listServiceActivityYearMonth", listServiceActivityYearMonth);
			//System.out.println(listServiceActivityYearMonth);
			ServiceActivity serviceActivityObject = new ServiceActivity();
			model.addAttribute("serviceActivityObject", serviceActivityObject);
			return "serviceactivity/all-service-activities";
		}

}
