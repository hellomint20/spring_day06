package com.care.root.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	public BoardServiceImpl() {
		System.out.println("board ser ����");
	}
	
	public List<BoardDTO> boardAllList(){
		return mapper.boardAllList();
	}
	
	public String writeSave(BoardDTO dto, MultipartFile file) {
		if(file.isEmpty()) { //������ ���� ���
			dto.setImageFileName("nan");
		}else { //������ �����ϴ� ���
			dto.setImageFileName(bfs.saveFile(file));
		}
		int result = mapper.writeSave(dto);
		String msg="", url="";
		if(result == 1) { //DB ���� ����
			msg = "���� �ۼ��Ǿ����ϴ�";
			url = "/root/board/boardAllList";
			// root -> request.getContextPath()
		}else { //DB ���� ����
			msg = "������ �߻��߽��ϴ�";
			url = "/root/board/writeForm";
		}
		
		return bfs.getMessage(msg, url);
	}
	
	public BoardDTO contentView(int writeNo) {
		return mapper.getContent(writeNo);
	}
}
