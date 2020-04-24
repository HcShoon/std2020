package com.shoon.bbs.bbs.service;

import java.util.List;

import com.shoon.bbs.bbs.vo.BoardVO;

public interface BoardService {
	// 게시글 작성
	public void write(BoardVO boardVO) throws Exception;
	// 게시글 리스트
	public List<BoardVO> list() throws Exception;
	//게시글 조회
	public BoardVO read(int bno) throws Exception;
	//게시글 수정
	public void update(BoardVO boardVO) throws Exception;
	//게시글 삭제
	public void delete(int bno) throws Exception;
}
