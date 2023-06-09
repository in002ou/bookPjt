package com.gdu.book.service;


import org.springframework.stereotype.Service;

import com.gdu.book.mapper.UserMapper;
import com.gdu.book.util.JavaMailUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	// 필드
	private UserMapper userMapper;
	private JavaMailUtil javaMailUtil;
	
	

	
	
	
	
	   
}
