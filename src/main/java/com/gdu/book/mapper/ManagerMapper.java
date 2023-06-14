package com.gdu.book.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.BookDTO;

@Mapper
public interface ManagerMapper {

	public int addBook(BookDTO bookDTO);
	
}
