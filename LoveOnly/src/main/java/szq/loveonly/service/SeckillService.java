package szq.loveonly.service;

import java.util.List;

import szq.loveonly.dto.Exposer;
import szq.loveonly.dto.SeckillExecution;
import szq.loveonly.entity.Seckill;
import szq.loveonly.exception.RepeatKillException;
import szq.loveonly.exception.SeckillCloseExcepion;
import szq.loveonly.exception.SeckillException;

/**
 * 业务接口:站在“使用者”的角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型(return 类型/异常)
 */

public interface SeckillService {

	/*
	 * 查询所有秒杀记录
	 * */
	public List<Seckill> getSeckillList();
	
	
	/**
	 * 查询单个秒杀记录
	 * @param seckillId
	 * @return
	 */
	public Seckill getById(long seckillId);
	
	
	/*
	 * 秒杀开启时输出秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 * */
	public Exposer exportSeckillUrl(long seckillId);
	
	
	/*
	 * 执行秒杀操作
	 * */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)throws SeckillException,RepeatKillException,SeckillCloseExcepion;
}
