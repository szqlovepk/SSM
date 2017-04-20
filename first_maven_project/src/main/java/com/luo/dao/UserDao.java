package com.luo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luo.domain.User;

public interface UserDao {
	
	/**
	 * @param userId
	 * @return User
	 */
	public User selectUserById(int userId);
	
	List<User> selectUserByUserName(@Param("userName") String userName);  

}
