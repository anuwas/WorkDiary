package com.org.pack.wd.team.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.application.repository.ApplicationsRepository;
import com.org.pack.wd.team.entiry.TeamMember;
import com.org.pack.wd.team.entiry.TeamMemberAppraisal;
import com.org.pack.wd.team.entiry.TeamMemberLeave;
import com.org.pack.wd.team.repository.TeamMemberAppraisalRepository;
import com.org.pack.wd.team.repository.TeamMemberLeaveRepository;
import com.org.pack.wd.team.repository.TeamMemberRepository;
import com.org.pack.wd.util.DiaryUtil;

@Controller
public class TeamController {
	
	@Autowired
	TeamMemberRepository teamMemberRepository;
	
	@Autowired
	TeamMemberAppraisalRepository teamMemberAppraisalRepository;
	
	@Autowired
	ApplicationsRepository applicationsRepository;
	
	@Autowired
	TeamMemberLeaveRepository teamMemberLeaveRepository;
	
	@GetMapping("/project-and-team")
	public String getProjectTeam(Model model) {
	
		List<TeamMember> allActiveTeamMemeber = teamMemberRepository.findAllByStatus("Active");
		model.addAttribute("allActiveTeamMemeber", allActiveTeamMemeber);	
		Map<Long,String> teamMemberNameIDMap = allActiveTeamMemeber.stream().collect(
				Collectors.toMap(TeamMember::getTeamMemberId, TeamMember::getFullName));
		model.addAttribute("year",String.valueOf(DiaryUtil.getCurrentYear()));
		List<TeamMemberLeave> currentDateLeave = teamMemberLeaveRepository.findAllByLeaveDate(DiaryUtil.getCurrentDate());
		List<TeamMemberLeave> upComingLeave = teamMemberLeaveRepository.findAllByLeaveDateGreaterThanOrderByLeaveDateAsc(DiaryUtil.getCurrentDate());
		if(upComingLeave.size()>=3) {
			upComingLeave = upComingLeave.subList(0, 3);
		}
		
		List<Applications> allApplications = applicationsRepository.findAll();
		
		model.addAttribute("currentDateLeave", currentDateLeave);
		model.addAttribute("upComingLeave", upComingLeave);
		model.addAttribute("teamMemberLeave", new TeamMemberLeave());
		model.addAttribute("teamMemberNameIDMap", teamMemberNameIDMap);
		model.addAttribute("allApplications", allApplications);
		return "team/project-team";
	}
	
	@GetMapping("/all-active-team-member")
	public String getAllActiveTeamMember(Model model) {
	
		List<TeamMember> allActiveTeamMemeber = teamMemberRepository.findAllByStatus("Active");
		model.addAttribute("allActiveTeamMemeber", allActiveTeamMemeber);
		model.addAttribute("year",String.valueOf(DiaryUtil.getCurrentYear()));
		
		return "team/all-team-member-list";
	}
	
	@GetMapping("/team-member-edit/{memberid}")
	public String getTeamMemberEditForm(Model model,@PathVariable long memberid) {
	
		Optional<TeamMember> teamMember = teamMemberRepository.findById(memberid);
		List listTaskStatus = Arrays.asList("Active","Inactive");
		model.addAttribute("teamMember", teamMember.get());
		model.addAttribute("memberid", memberid);
		model.addAttribute("listTaskStatus", listTaskStatus);
		
		
		return "team/team-member-edit-form";
	}
	
	@PostMapping("/team-member-update/{memberid}")
	public String updateTeamMember(TeamMember teamMember,Model model,@PathVariable long memberid) {
		teamMember.setTeamMemberId(memberid);
		teamMemberRepository.save(teamMember);
		
		return "redirect:/team-member-edit/"+memberid;
	}
	
	@GetMapping("/member-appraisal-detail/{memberid}/{year}")
	public String getTeamMemberAppraisalDetail(Model model,@PathVariable long memberid,@PathVariable String year) {
	
		Optional<TeamMember> teamMember = teamMemberRepository.findById(memberid);
		model.addAttribute("teamMember", teamMember.get());
		TeamMemberAppraisal teamMemberAppraisalObject = teamMember.get().getTeamMemberAppraisal().stream()
				.filter(team->team.getFinancialYear().equals(year)).findFirst().get();
		model.addAttribute("teamMemberAppraisalObject", teamMemberAppraisalObject);
		
		return "team/member-appraisal-detail";
	}
	
	@GetMapping("/member-appraisal-detail-edit/{memberappid}")
	public String updateTeamMemberAppraisalForm(Model model,@PathVariable long memberappid) {
		Optional<TeamMemberAppraisal> teamMember = teamMemberAppraisalRepository.findById(memberappid);
		model.addAttribute("teamMemberAppraisalObject", teamMember.get());
		return "team/member-appraisal-edit-form";
	}
	
	@PostMapping("/member-appraisal-detail-update/{teammemapprid}")
	public String updateTeamMemberAppraisalUpdate(TeamMemberAppraisal teamMemberAppraisal,Model model,@PathVariable long teammemapprid) {
		Optional<TeamMemberAppraisal> teamMember = teamMemberAppraisalRepository.findById(teammemapprid);
		TeamMemberAppraisal apprisalObject = teamMember.get();
		apprisalObject.setFinancialYear(teamMemberAppraisal.getFinancialYear());
		apprisalObject.setAssessmentSession(teamMemberAppraisal.getAssessmentSession());
		apprisalObject.setGoesWell(teamMemberAppraisal.getGoesWell());
		apprisalObject.setGoesNotWell(teamMemberAppraisal.getGoesNotWell());
		apprisalObject.setImprovementAreas(teamMemberAppraisal.getImprovementAreas());
		apprisalObject.setDeliveryStat(teamMemberAppraisal.getDeliveryStat());
		apprisalObject.setLeaveStat(teamMemberAppraisal.getLeaveStat());
		apprisalObject.setComments(teamMemberAppraisal.getComments());
		apprisalObject.setPR(teamMemberAppraisal.getPR());
		apprisalObject.setCR(teamMemberAppraisal.getCR());
		apprisalObject.setKA(teamMemberAppraisal.getKA());
		apprisalObject.setTool(teamMemberAppraisal.getTool());
		apprisalObject.setRca(teamMemberAppraisal.getRca());
		teamMemberAppraisalRepository.save(apprisalObject);
		return "redirect:/member-appraisal-detail/"+apprisalObject.getTeamMember().getTeamMemberId()+"/"+apprisalObject.getFinancialYear();
	}
	
	@PostMapping("/save-team-member-leave")
	public String saveTeamMemberLeave(TeamMemberLeave teamMemberLeave,Model model) {
		teamMemberLeaveRepository.save(teamMemberLeave);
		String financialYear = String.valueOf(DiaryUtil.getCurrentYear());
		TeamMember currentTeamMember = teamMemberRepository.findById(teamMemberLeave.getTeamMember().getTeamMemberId()).get();
		TeamMemberAppraisal teamMemberAprisal = teamMemberAppraisalRepository.findByTeamMemberAndFinancialYear(currentTeamMember, financialYear);
		int updatedLeaveCount = teamMemberAprisal.getLeaveCount()+1;
		teamMemberAprisal.setLeaveCount(updatedLeaveCount);
		teamMemberAppraisalRepository.save(teamMemberAprisal);
		return "redirect:/project-and-team";
	}
	
	@GetMapping("/member-leave-history/{memberid}")
	public String getTeamMemberLeaveHistory(Model model,@PathVariable long memberid) {
		Optional<TeamMember> teamMember = teamMemberRepository.findById(memberid);
		
		
		
		List<TeamMemberLeave> getAllLeave = teamMemberLeaveRepository.findAllByTeamMemberAndLeaveDateBetweenOrderByCreatedDateDesc(teamMember.get(),DiaryUtil.getFirstDateOfYear(),DiaryUtil.getLastDateOfYear());
		
		String financialYear = String.valueOf(DiaryUtil.getCurrentYear());
		TeamMemberAppraisal teamMemberAprisal = teamMemberAppraisalRepository.findByTeamMemberAndFinancialYear(teamMember.get(), financialYear);
		model.addAttribute("allTakenLeave", getAllLeave);
		model.addAttribute("memberFullName", teamMember.get().getFullName());
		model.addAttribute("ListCount", teamMemberAprisal.getLeaveCount());
		return "team/member-leave-list";
	}
	
	@GetMapping("/member-all-leave-history")
	public String getTeamMemberAllLeveHistory(Model model,@RequestParam(defaultValue = "1") int page) {
		List<TeamMemberLeave> teamLeaveList = new ArrayList<>();
		int size = 50;
		Pageable paging = PageRequest.of(page - 1, size,Sort.by("leaveDate").descending());
		Page<TeamMemberLeave> pageTuts = teamMemberLeaveRepository.findAll(paging);
		teamLeaveList = pageTuts.getContent();

	      model.addAttribute("allTeamMemberLeaveList", teamLeaveList);
	      model.addAttribute("currentPage", pageTuts.getNumber() + 1);
	      model.addAttribute("totalItems", pageTuts.getTotalElements());
	      model.addAttribute("totalPages", pageTuts.getTotalPages());
	      model.addAttribute("pageSize", size);
		return "team/all-leave-list";
	}

}
