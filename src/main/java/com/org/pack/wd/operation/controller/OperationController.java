package com.org.pack.wd.operation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.org.pack.wd.dto.OperationNameDescription;
import com.org.pack.wd.entity.ServiceActivity;
import com.org.pack.wd.operation.entity.Operation;
import com.org.pack.wd.operation.entity.OperationAudit;
import com.org.pack.wd.operation.repository.OperationAuditRepository;
import com.org.pack.wd.operation.repository.OperationRepository;

@Controller
public class OperationController {
	
	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	OperationAuditRepository operationAuditRepository;
	
	
	
	@GetMapping("/all-operations")
	public String allServiceActivities(Model model) {
	
		List<OperationNameDescription> listAllOperation = operationRepository.findAllOperationNameDescription();
		model.addAttribute("listAllOperation", listAllOperation);
		//System.out.println(listAllOperation);
		Operation operationObject = new Operation();
		model.addAttribute("operationObject", operationObject);
		return "operation/all-operations";
	}
	
	@PostMapping("/savenewoperation")
	public String createNewOperation(Operation operation,Model model) {
		try {
			operationRepository.save(operation);
			return "redirect:/all-operations";
		} catch (Exception e) {
			return "all-operations";
		}
	}
	
	@GetMapping("/operation/{operationid}")
	public String getOperationAudit(Model model,@PathVariable long operationid) {
		Optional<Operation> operationObj = operationRepository.findById(operationid);
		//List<OperationAudit> operationAuditObjectList = operationAuditRepository.findByOperation(operationObj.get());
		List<OperationAudit> operationAuditObjectList = operationObj.get().getOperationAudit();
		//operationObj.get().setOperation_id(operationid);
		OperationAudit operationAuditObject = new OperationAudit();
		//operationAuditObject.setOperation(operationObj.get());
		model.addAttribute("operationObject", operationObj.get());
		model.addAttribute("operationAuditObject", operationAuditObject);
		model.addAttribute("operationAuditObjectList", operationAuditObjectList);
		model.addAttribute("operationid", operationid);
		//System.out.println(operationObj.get().toString());
		//OperationAudit op = operationObj.get().getOperationAudit().get(0);
		return "operation/operation-audit";
	}
	
	
	@PostMapping("/save-operation-audit/{operationid}")
	public String createNewAudit(OperationAudit operationAudit,@PathVariable long operationid,Model model) {
		try {
			Optional<Operation> operationObj = operationRepository.findById(operationid);
			operationAudit.setOperation(operationObj.get());
			operationAuditRepository.save(operationAudit);
			return "redirect:/operation/"+operationid;
		} catch (Exception e) {
			e.printStackTrace();
			return "task-form";
		}
	}
	
	@GetMapping("/operation-audit-edit/{operationauditid}")
	public String operationAuditUpdate(Model model,@PathVariable long operationauditid) {
		Optional<OperationAudit> operationAuditObject = operationAuditRepository.findById(operationauditid);
		Operation operationObject = operationAuditObject.get().getOperation();
		
		model.addAttribute("operationAuditObject", operationAuditObject.get());
		model.addAttribute("operationObject", operationObject);
		//System.out.println(operationObj.get().toString());
		//OperationAudit op = operationObj.get().getOperationAudit().get(0);
		return "operation/operation-audit-edit";
	}
	
	@PostMapping("/operation-audit-update/{operationauditid}")
	public String updateOperationAudit(@ModelAttribute("operationAuditObject") OperationAudit operationAuditObject,@PathVariable long operationauditid,Model model) {
		try {
			
			Optional<OperationAudit> existingOperationAuditObject = operationAuditRepository.findById(operationauditid);
			existingOperationAuditObject.get().setOperationActivity(operationAuditObject.getOperationActivity());
			
			operationAuditRepository.save(existingOperationAuditObject.get());
			return "redirect:/operation/"+existingOperationAuditObject.get().getOperation().getOperationId();
		} catch (Exception e) {
			e.printStackTrace();
			return "task-form";
		}
	}

}
