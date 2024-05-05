package com.org.pack.wd.team.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.pack.wd.operation.entity.OperationAudit;
import com.org.pack.wd.team.entiry.TeamMember;
import com.org.pack.wd.team.entiry.TeamMemberAppraisal;
import com.org.pack.wd.team.repository.TeamMemberAppraisalRepository;
import com.org.pack.wd.team.repository.TeamMemberRepository;
import com.org.pack.wd.util.DiaryUtil;

@Controller
public class TeamController {
	
	@Autowired
	TeamMemberRepository teamMemberRepository;
	
	@Autowired
	TeamMemberAppraisalRepository teamMemberAppraisalRepository;
	
	@GetMapping("/project-and-team")
	public String getProjectTeam(Model model) {
	
				
		return "team/project-team";
	}
	
	@GetMapping("/all-active-team-member")
	public String getAllActiveTeamMember(Model model) {
	
		List<TeamMember> allActiveTeamMemeber = teamMemberRepository.findAllByStatus("Active");
		model.addAttribute("allActiveTeamMemeber", allActiveTeamMemeber);
		model.addAttribute("year",String.valueOf(DiaryUtil.getCurrentYear()));
		
		return "team/all-team-member-list";
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
		teamMemberAppraisalRepository.save(apprisalObject);
		return "redirect:/member-appraisal-detail/"+apprisalObject.getTeamMember().getTeamMemberId()+"/"+apprisalObject.getFinancialYear();
	}

}
