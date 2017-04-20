package com.luo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.luo.domain.User;
import com.luo.service.UserService;

@Controller  
public class UserController {  
	
    @Resource  
    private UserService userService;  
      
    @RequestMapping("/list")    
    public ModelAndView getIndex(Integer pageNum, Integer pageSize){      
        ModelAndView mav = new ModelAndView("index");   
        //User user = userService.selectUserById(1);  
     
        
       // System.out.println(pageNum+pageSize+"55555555");
        PageInfo<User> page = userService.queryByPage(null, pageNum, pageSize);
        
        mav.addObject("page", page);   
        return mav;    
    }    
}  
