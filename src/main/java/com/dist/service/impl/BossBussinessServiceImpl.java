package com.dist.service.impl;
import com.dist.bean.Boss;
import com.dist.service.BossBussinessService;
import com.dist.service.impl.BossBussinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;





@Service
public class BossBussinessServiceImpl
  implements BossBussinessService
{
  @Autowired
  JdbcTemplate jdbcTemplate;
  
  public void saveBoss(Boss boss) {
    String sql = "insert into boss(flush_time,map_name,minute,name,hour,second,daojishi,dead_time,y,x,tic) values(?,?,?,?,?,?,?,?,?,?,?)";

    
    Object[] params = { 
        boss.getFlush_time(), 
        boss.getMap_name(), 
        boss.getMinute(), 
        boss.getName(), 
        boss.getHour(), 
        boss.getSecond(), 
        boss.getDaojishi(), 
        boss.getDead_time(), 
        boss.getY(), 
        boss.getX(), 
        boss.getTic() };

    
    this.jdbcTemplate.update(sql, params);
  }



  
  public void removeBossById(String boss_id) {
    String sql = "delete from boss where id = ? ";
    Object[] params = {
        boss_id
      };
    this.jdbcTemplate.update(sql, params);
  }






  
  public void updateBossById(Boss boss) {
    String sql = "update boss set dead_time = ? ,tic = ?  where id = ? ";
    Object[] params = {
        boss.getDead_time(), 
        boss.getTic(), 
        boss.getId()
      };
    this.jdbcTemplate.update(sql, params);
  }
}
