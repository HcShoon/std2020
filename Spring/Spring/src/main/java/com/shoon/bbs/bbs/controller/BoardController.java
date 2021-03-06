package com.shoon.bbs.bbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoon.bbs.bbs.service.BoardService;
import com.shoon.bbs.bbs.vo.BoardVO;
import com.shoon.bbs.bbs.vo.Criteria;
import com.shoon.bbs.bbs.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService service;
	
	// 게시판 글 작성 화면
		@RequestMapping(value = "/writeView", method = RequestMethod.GET)
		public void writeView() throws Exception{
			logger.info("writeView");
			
		}
		
		// 게시판 글 작성
		@RequestMapping(value = "/write", method = RequestMethod.POST)
		public String write(BoardVO boardVO) throws Exception{
			logger.info("write");
			
			service.write(boardVO);
			
			return "redirect:list";
		}
		
		// 게시판 목록 조회
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public String list(Model model, Criteria cri) throws Exception{
			logger.info("list");
		
			model.addAttribute("list",service.list(cri));
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.listCount());
			
			model.addAttribute("pageMaker", pageMaker);
			System.out.println(service.listCount());
			
			return "board/list";
			
		}
		// 게시판 조회
		@RequestMapping(value = "/readView", method = RequestMethod.GET)
		public String read(BoardVO boardVO, Model model) throws Exception{
			logger.info("read");
			
			model.addAttribute("read", service.read(boardVO.getBno()));
			
			return "board/readView";
		}
		
		// 게시판 수정
		@RequestMapping(value = "/updateView", method = RequestMethod.GET)
		public String updateView(BoardVO boardVO, Model model) throws Exception{
			logger.info("updateView");
			String bno = "boardVO.getBno() : " + boardVO.getBno();
			logger.info(bno);
			model.addAttribute("updateView", service.read(boardVO.getBno()));
			
			return "board/updateView";
		}
		
		// 게시판 수정
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update(BoardVO boardVO, Model model) throws Exception{
			logger.info("update");
			
			service.update(boardVO);
			
			return "redirect:list";
		}
		
		// 게시판 삭제
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String delete(BoardVO boardVO, Model model) throws Exception{
			logger.info("delete");
			
			service.delete(boardVO.getBno());
			
			return "redirect:list";
		}
				
				
		
}
