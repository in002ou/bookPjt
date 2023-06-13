package com.gdu.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.BookDTO;
import com.gdu.book.domain.BookReviewDTO;


@Mapper
public interface BookMapper {
	public BookDTO selectBookDetail(int bookNo);
	public int insertBookReview(BookReviewDTO bookReviewDTO);
	public List<BookReviewDTO> selectBookReview(int bookNo);
}
