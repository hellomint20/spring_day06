package com.care.root.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.LoginSession;
import com.care.root.dto.MemberDTO;
import com.care.root.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired MemberService ms;

	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	@PostMapping("logChk")
	public String logChk(@RequestParam String id, @RequestParam String pw, 
							HttpSession session, RedirectAttributes rs) {
		int result = ms.logChk(id, pw);
		if(result == 0) {
			//LOGIN 이 가지고 있는 값이 login이므로 세션 이름은 "login" 생성
			//session.setAttribute(LoginSession.LOGIN, id);
			//return "redirect:list";
			rs.addAttribute("id", id);
			return "redirect:successLogin";
		}
		return "redirect:login";	
	}
	@GetMapping("successLogin")
	public String successLogin(@RequestParam String id, HttpSession session) {
		session.setAttribute(LoginSession.LOGIN, id);
		return "member/successLogin";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("list", ms.getList());
		return "member/list";
	}
	@GetMapping("register_view")
	public String registerView() {
		return "member/register_view";
	}
	@PostMapping("register")
	public String register(HttpServletRequest req, MemberDTO dto) {		
		ms.register(dto, req.getParameterValues("addr"));
		return "redirect:login";
	}
	@GetMapping("info")
	public String info(@RequestParam String id, Model model) {
		model.addAttribute("mem", ms.getMember(id));
		return "member/info";
	}
	@PostMapping("modify")
	public String modify(MemberDTO dto, HttpServletRequest req) {
		ms.modify(dto, req.getParameterValues("addr"));
		return "redirect:list";
	}
}
