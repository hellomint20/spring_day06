package com.care.root.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired BoardService bs;
	
	public BoardController() {
		System.out.println("board con ½ÇÇà");
	}
	
	@GetMapping("boardAllList")
	public String boardAllList(Model model) {
		model.addAttribute("list", bs.boardAllList());
		return "board/boardAllList";
	}
	
	@GetMapping("writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@PostMapping("writeSave")
	public void writeSave(BoardDTO dto, @RequestParam(required = false) MultipartFile image_file_name,
							HttpServletResponse res) throws IOException {
		System.out.println("content : "+dto.getContent());
		String msg = bs.writeSave(dto, image_file_name);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	@GetMapping("content_view")
	public String contentView(@RequestParam int writeNo, Model model) {
		model.addAttribute("dto", bs.contentView(writeNo));
		return "board/contentView";
	}
	@GetMapping("download")
	public void download(@RequestParam String name, HttpServletResponse res) throws Exception{
		res.addHeader("Content-disposition", "attachment; fileName="+URLEncoder.encode(name, "utf-8"));
		File file = new File(BoardFileService.IMAGE_REPO+"/"+name);
		FileInputStream in = new FileInputStream(file);
		FileCopyUtils.copy(in, res.getOutputStream());
		in.close();
	}
	@GetMapping("modify_form")
	public String modifyForm() {
		return "board/modifyForm";
	}
}
