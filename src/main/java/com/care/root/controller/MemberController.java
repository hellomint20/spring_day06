package com.care.root.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
							HttpSession session, RedirectAttributes rs,
							@RequestParam(required = false, defaultValue = "off") String autoLogin) {
							//required 안해주면 값이 없어서 400 오류남, null 값으로 넘어가면 오류 발생하기 때문에 defaultValue 지정함
		int result = ms.logChk(id, pw);
		if(result == 0) {
			//LOGIN 이 가지고 있는 값이 login이므로 세션 이름은 "login" 생성
			//session.setAttribute(LoginSession.LOGIN, id);
			//return "redirect:list";
			rs.addAttribute("id", id);
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successLogin";
		}
		return "redirect:login";	
	}
	@GetMapping("successLogin")
	public String successLogin(@RequestParam String id, HttpSession session,
								@RequestParam String autoLogin, HttpServletResponse res) {
		System.out.println("autoLogin : "+autoLogin);
		if(autoLogin.equals("on")) {
			int limitTime = 60*60*24*90; //90일 설정
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			
			ms.keepLogin(session.getId(), id);
		}
		session.setAttribute(LoginSession.LOGIN, id);
		return "member/successLogin";
	}
	@GetMapping("logout")
	public String logout(HttpSession session, @CookieValue(required = false) Cookie loginCookie,
							HttpServletResponse res) {
		if(loginCookie != null) {
			loginCookie.setMaxAge(0); //쿠키값 삭제
			res.addCookie(loginCookie);
			ms.keepLogin("nan", (String)session.getAttribute(LoginSession.LOGIN));
		}
		session.invalidate();
		//return "redirect:login";
		return "redirect:/index";
	}
	@GetMapping("list")
	public String list(Model model) {
		System.out.println("=== memcon list 실행 ===");
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
