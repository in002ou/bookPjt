package com.gdu.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.CartDTO;


@Mapper
public interface CartMapper {
	public int insertCart(CartDTO cartDTO);
	public List<CartDTO> selectCartList(int userNo);
	public int deleteCart(List<String> cartNoList);
}
