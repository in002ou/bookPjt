package com.gdu.book.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.BookReviewDTO;
import com.gdu.book.domain.BookSearchDTO;
import com.gdu.book.domain.UserRecommenedDTO;


@Mapper
public interface BookMapper {
	public int getBookCount();
	public List<BookDTO> selectBookList(Map<String, Object> map);
	public List<BookDTO> searchBook(BookSearchDTO bookSearchDTO); 
	public BookDTO selectBookDetail(String bookNo);
	public int insertBookReview(BookReviewDTO bookReviewDTO);
	public List<BookReviewDTO> selectBookReview(String bookNo);
	public int deleteBookReview(String bookReviewNo);
	public int addBookLike(UserRecommenedDTO userRecommenedDTO);
	public int addBookDisLike(UserRecommenedDTO userRecommenedDTO);
	public UserRecommenedDTO selectUserLike(UserRecommenedDTO userRecommenedDTO);
	public int updateUserLike(UserRecommenedDTO userRecommenedDTO); 
	public int updateUserDisLike(UserRecommenedDTO userRecommenedDTO); 
}
