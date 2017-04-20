package szq.loveonly.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import szq.loveonly.entity.Seckill;

public interface SeckillDao {

	//减库存  return 如果影响行数>1 表示更新的记录行数
	public int reduceNum(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	//根据seckillId查询秒杀对象
	public Seckill queryById(long seckillId);
	
	//根据偏移量查询秒杀列表
	public List<Seckill> queryAll(@Param("offset")int offset, @Param("limit") int limit);
}
