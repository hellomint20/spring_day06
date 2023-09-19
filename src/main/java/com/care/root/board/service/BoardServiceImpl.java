package com.care.root.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	public BoardServiceImpl() {
		System.out.println("board ser 실행");
	}
	public Map<String, Object> boardAllList(int num){
		int pageLetter = 3; //몇 개 글
		int allCount = mapper.selectBoardCount(); //글 총 개수
		int repeat = allCount/pageLetter; //총 페이지 수
		if(allCount/pageLetter != 0) {
			repeat++;
		}
		int end = num * pageLetter;
		int start = end+1-pageLetter;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.boardAllList(start, end));
		
		return map;
	}
	public String writeSave(BoardDTO dto, MultipartFile file) {
		if(file.isEmpty()) { //파일이 없는 경우
			dto.setImageFileName("nan");
		}else { //파일이 존재하는 경우
			dto.setImageFileName(bfs.saveFile(file));
		}
		int result = mapper.writeSave(dto);
		String msg="", url="";
		if(result == 1) { //DB 저장 성공
			msg = "글이 작성되었습니다";
			url = "/root/board/boardAllList";
			// root -> request.getContextPath()
		}else { //DB 저장 실패
			msg = "문제가 발생했습니다";
			url = "/root/board/writeForm";
		}
		
		return bfs.getMessage(msg, url);
	}
	public BoardDTO contentView(int writeNo) {
		upHit(writeNo);
		return mapper.getContent(writeNo);
	}
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}
	public BoardDTO getContent(int writeNo) {
		return mapper.getContent(writeNo);
	}
	public String modify(BoardDTO dto, MultipartFile file) {
		String originName = null;
		
		if(!file.isEmpty()) { //이미지 수정 됨
			originName = dto.getImageFileName();
			dto.setImageFileName(bfs.saveFile(file));
		}
		
		int result = mapper.modify(dto);
		
		String msg="", url="";
		if(result == 1) { //수정 성공
			//폴더에 기존 이미지 삭제
			bfs.deleteImage(originName);
			msg = "게시글이 수정 되었습니다😀😀";
			url = "/root/board/content_view?writeNo="+dto.getWriteNo();
			// root -> request.getContextPath()
		}else { //수정 실패
			//수정 이미지 삭제
			bfs.deleteImage(dto.getImageFileName());
			msg = "문제가 발생했습니다";
			url = "/root/board/modify_form?writeNo="+dto.getWriteNo();
		}
		return bfs.getMessage(msg, url);
	}
	public String delete(int writeNo, String fileName) {
		int result = mapper.delete(writeNo);
		
		String msg="", url="";
		if(result == 1) { //삭제 성공
			bfs.deleteImage(fileName); //폴더에 이미지 삭제
			msg = "게시글이 삭제 되었습니다😀😀";
			url = "/root/board/boardAllList";
			// root -> request.getContextPath()
		}else { //삭제 실패
			msg = "문제가 발생했습니다";
			url = "/root/board/content_view?writeNo="+writeNo;
		}
		return bfs.getMessage(msg, url);
	}
	public void addReply(BoardRepDTO dto) {
		mapper.addReply( dto );
	}
	public List<BoardRepDTO> getRepList(int write_group){
		return mapper.getRepList(write_group);
	}
}
