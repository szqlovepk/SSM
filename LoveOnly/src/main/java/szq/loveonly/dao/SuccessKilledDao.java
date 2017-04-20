package szq.loveonly.dao;

import szq.loveonly.entity.Successkilled;

public interface SuccessKilledDao {

	//插入购买明细，可重复过滤   return插入的行数
	public int insertSuccessKilled(long seckillId, long userPhone);
	
	//根据Id查询SuccessKilled并携带秒杀产品对象实体
	public Successkilled queryByIdWithSeckill(long seckillId);
}
