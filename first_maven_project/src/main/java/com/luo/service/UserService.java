package com.luo.service;

import com.github.pagehelper.PageInfo;
import com.luo.domain.User;

public interface UserService {
	
	User selectUserById(int userId);  
	
	PageInfo<User> queryByPage(String userName,Integer pageNo,Integer pageSize); 
}
