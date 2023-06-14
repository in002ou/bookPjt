package com.gdu.book.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.book.domain.UserDTO;
import com.gdu.book.domain.UserLeaveDTO;
import com.gdu.book.domain.UserSleepDTO;


@Mapper
public interface UserMapper {
	
	  public UserDTO selectUserById(String userId);
	  public UserSleepDTO selectSleepUserById(String userSleepId);
	  public UserLeaveDTO selectLeaveUserById(String userLeaveId);
	  public UserDTO selectUserByEmail(String userEmail);
	  public UserSleepDTO selectSleepUserByEmail(String userSleepEmail);
	  public UserLeaveDTO selectLeaveUserByEmail(String userLeaveEmail);
	  public int insertUser(UserDTO userDTO);
	  public UserDTO selectUserByUserDTO(UserDTO userDTO);
	  public int insertUserAccess(String userId);
	  public int updateUserAccess(String userId);
	  public int insertAutologin(UserDTO userDTO);
	  public int deleteAutologin(String userId);
	  public UserDTO selectAutologin(String autologinId);
	  public int insertLeaveUser(UserLeaveDTO userLeaveDTO);
	  public int deleteUser(String userId);
	  public int insertSleepUser();
	  public int deleteUserForSleep();
	  public int insertRestoreUser(String userId);
	  public int deleteSleepUser(String userId);
	
}
