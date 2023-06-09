package com.gdu.book.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.BookReviewDTO;


@Mapper
public interface BookMapper {
	public int insertBookReview(BookReviewDTO bookReviewDTO);
}
