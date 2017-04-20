package com.luo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luo.dao.UserDao;
import com.luo.domain.User;

@Service  
public class UserServiceImpl implements UserService {

	@Autowired  
    private UserDao userDao;  
  
    public User selectUserById(int userId) {  
        return userDao.selectUserById(userId);  
          
    }

	public PageInfo<User> queryByPage(String userName, Integer pageNo,
			Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?3:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<User> list = userDao.selectUserByUserName(userName);
		//用PageInfo对结果进行包装
		PageInfo<User> page = new PageInfo<User>(list);
		//测试PageInfo全部属性
//		System.out.println(page.getPageNum());//当前页
//		System.out.println(page.getPageSize());//每页的数量
//		System.out.println(page.getSize());//当前页的数量
//		//可以在页面中"显示startRow到endRow 共size条数据"
//		System.out.println(page.getStartRow());//当前页面第一个元素在数据库中的行号
//		System.out.println(page.getEndRow());//当前页面最后一个元素在数据库中的行号
//		System.out.println(page.getTotal());//总记录数
//		System.out.println(page.getPages());//总页数
//		System.out.println(page.getFirstPage());//第一页
//		System.out.println(page.getLastPage());//最后一页
//		System.out.println(page.getPrePage());//前一页
//		System.out.println(page.getNextPage());//下一页
//		System.out.println(page.isIsFirstPage());//是否为第一页
//		System.out.println(page.isIsLastPage());//是否为最后一页
//		System.out.println(page.isHasPreviousPage());//是否有前一页
//		System.out.println(page.isHasNextPage());//是否有下一页
		return page;
	}  
}
