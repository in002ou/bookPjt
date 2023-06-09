package com.gdu.book.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.UserDTO;


@Mapper
public interface UserMapper {
	public UserDTO selectUserById(String id);
	
}
