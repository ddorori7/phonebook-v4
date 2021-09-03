package com.phonebook.dao;

import java.util.List;

import com.phonebook.vo.PhoneBookVO;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList(); // 전체목록
	public List<PhoneBookVO> search(String keyword); // 검색 목록
	public boolean insert(PhoneBookVO vo); // 삽입
//	public boolean update(PhoneBookVO vo); // 갱신
	public boolean delete(Long id); // 삭제
}
