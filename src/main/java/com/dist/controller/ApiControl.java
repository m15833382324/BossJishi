package com.dist.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dist.utils.RedisUtil;


@RestController
@RequestMapping("/api/room")
public class ApiControl {
	
	@Resource
    private RedisUtil redisUtil;
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		System.out.println("进入到了编辑界面!");
		
		return null;
	}
}
