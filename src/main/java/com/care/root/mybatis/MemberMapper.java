package com.care.root.mybatis;

import java.util.List;

import com.care.root.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO getMember(String id);
	public List<MemberDTO> getList();
	public void register(MemberDTO dto);
	public void modify(MemberDTO dto);
}
