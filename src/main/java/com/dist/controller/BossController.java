package com.dist.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dist.bean.Boss;
import com.dist.service.BossBussinessService;
import com.dist.utils.DateTool;
import com.dist.utils.RedisUtil;

@RestController
@RequestMapping("/boss")
public class BossController {

	@Resource
    private RedisUtil redisUtil;
	
	@Resource
	BossBussinessService bossService;
	
	@RequestMapping("/create")
	public String create(HttpServletRequest request) {
		
		
		
		//在这里接收到了几个参数.
		String boss_name=request.getParameter("boss_name");
		String map_name=request.getParameter("map_name");
	    String x=request.getParameter("x");
	    String y=request.getParameter("y");
	    String flush_time=request.getParameter("flush_time");
	    
	    //然后组织一个对象.
	    Boss  boss = new Boss();
	    Date date = new Date();
	    //然后将这个时间进行整理.
	    boss.setName(boss_name);
	    boss.setDead_time(DateTool.getDateTime(date,"HH:mm:ss"));
	    boss.setMap_name(map_name);
	    boss.setX(x);
	    boss.setY(y);
	    boss.setFlush_time(flush_time);
	    boss.setTic(String.valueOf(date.getTime()));
	    //id这个如何生成呢.
//	    jdbcTemplate.batchUpdate(sql, batchArgs)
	    //然后保存数据.
	    bossService.saveBoss(boss);
	    
		return null;
	}
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request) {
		String boss_id= request.getParameter("boss_id");
		//然后,将这个id,
		bossService.removeBossById(boss_id);
		return null;
	}
	
	@RequestMapping("/update/{path}")
	public String update(HttpServletRequest request,@PathVariable("path") String path) {
		
		String boss_id= request.getParameter("boss_id");
		//然后更新时间.
		//首先创建时间.
		Date date = new Date();
		Date newDate=null;
		//然后转换为两个参数.
		Long dateLong = date.getTime();
		//这里主要更新两个参数,一个是 boss的tic 一个是boss的date_time;其实主要还是更新boss的date_time.
		Boss boss = new Boss();
		boss.setId(boss_id);
		switch(path) {
			//这里面分为四种情况,si,jian,shi,yao.
			case "si":
				//这个时间进行处理,
				//si的话就不需要处理.
				boss.setDead_time(DateTool.getDateTime(date, "HH:mm:ss"));
				boss.setTic(String.valueOf(date.getTime()));
				break;
			case "jian" :
				//jian是时间要往前推2分钟,即为.
				dateLong=dateLong-2*60*1000;
				//这个是更新2分钟的.
				newDate= new Date(dateLong);
				boss.setDead_time(DateTool.getDateTime(newDate, "HH:mm:ss"));
				boss.setTic(String.valueOf(dateLong));
				break;
			case "shi":
				dateLong=dateLong-5*60*1000;
				//这个是更新2分钟的.
				newDate = new Date(dateLong);
				boss.setDead_time(DateTool.getDateTime(newDate, "HH:mm:ss"));
				boss.setTic(String.valueOf(dateLong));
				break;
			case "yao":
				dateLong=dateLong-3*60*1000;
				//这个是更新2分钟的.
				newDate = new Date(dateLong);
				boss.setDead_time(DateTool.getDateTime(newDate, "HH:mm:ss"));
				boss.setTic(String.valueOf(dateLong));
				
				break;
			default:
				
				break;
		}
		
		bossService.updateBossById(boss);
		
		return null;
	}
}
