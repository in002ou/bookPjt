package com.gdu.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.BookReviewDTO;
import com.gdu.book.domain.BookSearchDTO;
import com.gdu.book.domain.UserDTO;
import com.gdu.book.domain.UserRecommenedDTO;
import com.gdu.book.mapper.BookMapper;
import com.gdu.book.util.PageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookMapper bookMapper;
	private final PageUtil pageUtil;
	
	@Override
	public void getBookList(HttpServletRequest request, Model model) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = bookMapper.getBookCount();
		
		int recordPerPage = 10;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<BookDTO> bookList = bookMapper.selectBookList(map);
		model.addAttribute("bookList", bookList);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getRequestURI()));
	}
	
	@Override
	public Map<String, Object> searchBook(BookSearchDTO bookSearchDTO) {
		List<BookDTO> list = bookMapper.searchBook(bookSearchDTO);
		
		String message = null;
		if(list.isEmpty()) {
			message = bookSearchDTO.getSearchText() + " 의 검색결과가 없습니다";
		} else {
			message = list.size() + "개의 검색 결과가 있습니다";
		}
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list);
	    map.put("message", message);
	    
	    return map;
	}
	
	@Override
	public void getBookDetail(HttpServletRequest request, Model model) {
		String bookNo = request.getParameter("bookNo");
		BookDTO bookDetail = bookMapper.selectBookDetail(bookNo);
		List<BookReviewDTO> bookReview = bookMapper.selectBookReview(bookNo);
		
		UserRecommenedDTO userRecommenedDTO = new UserRecommenedDTO();
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookNo(bookNo);
		UserDTO userDTO = new UserDTO();
		userDTO.setUserNo(1);
		userRecommenedDTO.setBookDTO(bookDTO);
		userRecommenedDTO.setUserDTO(userDTO);
		UserRecommenedDTO userLikeDTO   = bookMapper.selectUserLike(userRecommenedDTO);
		
		model.addAttribute("userLike", userLikeDTO == null ? 0 : userLikeDTO.getRecommened());
		model.addAttribute("bookDetail", bookDetail);
		model.addAttribute("bookReview", bookReview);
	}
	
	@Override
	public int addBookReview(HttpServletRequest request) {
		String review = request.getParameter("review");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String bookNo = request.getParameter("bookNo");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		BookReviewDTO bookReviewDTO = new BookReviewDTO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserNo(userNo); // html에서 세션 스코프 사용해서 넘겨줘야함 (동형이 등록 코드 완성 후)
		bookReviewDTO.setBookReview(review);
		bookReviewDTO.setBookRating(rating);
		bookReviewDTO.setUserDTO(userDTO);
		bookReviewDTO.setBookNo(bookNo);
		
		int addResult = bookMapper.insertBookReview(bookReviewDTO);
		return addResult;
	}
	
	@Override
	public int deleteBookReview(HttpServletRequest request) {
		String bookReviewNo = request.getParameter("bookReviewNo");
		
		int deleteResult = bookMapper.deleteBookReview(bookReviewNo);
		return deleteResult;
	}
	
	@Override
	public int bookLike(HttpServletRequest request, Model model) {
		int recommened = 1;
		String bookNo = request.getParameter("bookNo");
		
		BookDTO bookDTO = new BookDTO();
		UserDTO userDTO = new UserDTO();
		bookDTO.setBookNo(bookNo);
		userDTO.setUserNo(1);
		
		UserRecommenedDTO userRecommenedDTO = new UserRecommenedDTO();
		userRecommenedDTO.setBookDTO(bookDTO);
		userRecommenedDTO.setUserDTO(userDTO);
		userRecommenedDTO.setRecommened(recommened);
		UserRecommenedDTO list = bookMapper.selectUserLike(userRecommenedDTO);
		
		if(list == null) {
			bookMapper.addBookLike(userRecommenedDTO);
			UserRecommenedDTO list2 = bookMapper.selectUserLike(userRecommenedDTO);
			return list2.getRecommened();
		} else {
			bookMapper.updateUserLike(userRecommenedDTO);
		}
		

		return list.getRecommened();
	}
	
	@Override
	public int bookDisLike(HttpServletRequest request, Model model) {
		int recommened = -1;
		String bookNo = request.getParameter("bookNo");
		
		BookDTO bookDTO = new BookDTO();
		UserDTO userDTO = new UserDTO();
		bookDTO.setBookNo(bookNo);
		userDTO.setUserNo(1);
		
		UserRecommenedDTO userRecommenedDTO = new UserRecommenedDTO();
		userRecommenedDTO.setBookDTO(bookDTO);
		userRecommenedDTO.setUserDTO(userDTO);
		userRecommenedDTO.setRecommened(recommened);
		
		UserRecommenedDTO list = bookMapper.selectUserLike(userRecommenedDTO);
		if(list == null) {
			bookMapper.addBookDisLike(userRecommenedDTO);
			UserRecommenedDTO list2 = bookMapper.selectUserLike(userRecommenedDTO);
			return list2.getRecommened();
		} else {
			bookMapper.updateUserDisLike(userRecommenedDTO);
		}
		
		return list.getRecommened();
	}
}
