package com.shoon.bbs.bbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shoon.bbs.bbs.vo.BoardVO;
import com.shoon.bbs.bbs.vo.Criteria;
@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert("boardMapper.insert",boardVO);

	}
	
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.read", bno);
	}
	@Override
	public void update(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("boardMapper.update", boardVO);
	}
	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("boardMapper.delete", bno);
		
	}

	@Override
	public List<BoardVO> list(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.listPage", cri);
	}

	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.listCount");
	}

}
