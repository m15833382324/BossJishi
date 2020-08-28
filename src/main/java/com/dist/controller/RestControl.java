package com.dist.controller;


import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dist.utils.MapSortUtil;
import com.dist.utils.RedisUtil;

import cn.hutool.core.date.DateUtil;



@RestController
@RequestMapping("/app")
public class RestControl {
	
	@Resource
    private RedisUtil redisUtil;
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/start")
	@ResponseBody
	public String start() {
		return "开始";
	}

	/**
	 * 在这里使用boss定时器.
	 * 
	 * @return
	 */
	@RequestMapping("/roominfo")
	@ResponseBody
	public List<Map<String, Object>> getRoomMsg() {

//		String msg = "[{\"flush_time\": 3600, \"map_name\": \"\", \"second\": 50, \"id\": 483, "
//				+ "\"minute\": 25, \"name\": \"3123\", \"hour\": 10, \"daojishi\": \"<font color=red>0:44:31</font>\", "
//				+ "\"dead_time\": \"10:25:50\", \"y\": \"\", \"x\": \"\", \"tic\": 1598325950}]";
		
		//这里这个接口是频繁调用的.
		//然后从数据库获取当前时间.
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM boss");
		//然后针对这个时间进行处理.
		for (Map<String, Object> map : list) {
			Long ltime = Long.valueOf((String)map.get("flush_time"));
			//flushtime 是秒,通过这个秒来计算剩余的倒计时.
			//死亡的精确时间是tic,然后这个tci+当前时间-tic就是剩余时间.
			Long dateLong = Long.valueOf((String)map.get("tic"));
			Long daojishi = new Date().getTime()-dateLong;
			//然后这个daojishi就可以通过计算来处理了.
			if(daojishi>0&&daojishi<ltime*1000) {
				//然后计算出这个时间.
				String daojishiStr = DateUtil.secondToTime(ltime.intValue()-(daojishi.intValue()/1000));
				map.put("daojishi", "<font color=red>"+daojishiStr+"</font>");
				map.put("order", ltime.intValue()-(daojishi.intValue()/1000));
			}else if(daojishi>=3600) {
				map.put("order", 0);
				map.put("daojishi", "<font color=red>已刷新</font>");
			}
		}
		return MapSortUtil.sortMapAscInteger(list, "order");
	}
}
