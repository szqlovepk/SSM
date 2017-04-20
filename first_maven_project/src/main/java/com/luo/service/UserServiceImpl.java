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
		//��PageInfo�Խ�����а�װ
		PageInfo<User> page = new PageInfo<User>(list);
		//����PageInfoȫ������
//		System.out.println(page.getPageNum());//��ǰҳ
//		System.out.println(page.getPageSize());//ÿҳ������
//		System.out.println(page.getSize());//��ǰҳ������
//		//������ҳ����"��ʾstartRow��endRow ��size������"
//		System.out.println(page.getStartRow());//��ǰҳ���һ��Ԫ�������ݿ��е��к�
//		System.out.println(page.getEndRow());//��ǰҳ�����һ��Ԫ�������ݿ��е��к�
//		System.out.println(page.getTotal());//�ܼ�¼��
//		System.out.println(page.getPages());//��ҳ��
//		System.out.println(page.getFirstPage());//��һҳ
//		System.out.println(page.getLastPage());//���һҳ
//		System.out.println(page.getPrePage());//ǰһҳ
//		System.out.println(page.getNextPage());//��һҳ
//		System.out.println(page.isIsFirstPage());//�Ƿ�Ϊ��һҳ
//		System.out.println(page.isIsLastPage());//�Ƿ�Ϊ���һҳ
//		System.out.println(page.isHasPreviousPage());//�Ƿ���ǰһҳ
//		System.out.println(page.isHasNextPage());//�Ƿ�����һҳ
		return page;
	}  
}
