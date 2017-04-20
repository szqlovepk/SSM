package szq.loveonly.service.imp;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import szq.loveonly.dao.SeckillDao;
import szq.loveonly.dao.SuccessKilledDao;
import szq.loveonly.dto.Exposer;
import szq.loveonly.dto.SeckillExecution;
import szq.loveonly.entity.Seckill;
import szq.loveonly.entity.Successkilled;
import szq.loveonly.enums.SeckillStateEnum;
import szq.loveonly.exception.RepeatKillException;
import szq.loveonly.exception.SeckillCloseExcepion;
import szq.loveonly.exception.SeckillException;
import szq.loveonly.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	//md5盐值字符串  用于混淆md5
	private final String slat = "andkakc/k/aklcsjajdiiwuduu%^&**sndcj";
	
	@Override
	public List<Seckill> getSeckillList() {
		// TODO Auto-generated method stub
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		// TODO Auto-generated method stub
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		// TODO Auto-generated method stub
		Seckill seckill = seckillDao.queryById(seckillId);
		if(seckill == null){
			
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		//系统当前时间
		Date nowTime = new Date();
		if(nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()){
			
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		
		//转化特定字符串的过程 不可逆
		String md5 = getMD5(seckillId);
		
		return new Exposer(true, md5, seckillId);
	}
	
	//生成MD5
	private String getMD5(long seckillId){
		
		//自己定义的规则
		String base = seckillId + "/" + slat;
		//加密
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点：
	 * 1：开发团队达成一致约定   明确标注事务方法的编程风格
	 * 2：保证事务方法的执行时间尽可能短   不要穿插其他的网络操作
	 * 3：不是所有的方法都需要事务  如只有一条修改操作  只读操作不需要事务控制
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseExcepion {
		// TODO Auto-generated method stub
		if(md5 == null || !md5.equals(getMD5(seckillId))){
			
			throw new SeckillException("seckill data rewrite:");
		}
		
		//执行秒杀逻辑：减库存 + 记录购买行为
		Date nowTime = new Date();
		
		try {
			//减库存
			int updateCount = seckillDao.reduceNum(seckillId, nowTime);
			if(updateCount <= 0){
				
				//没有更新到记录 秒杀结束
				throw new SeckillCloseExcepion("seckill is closed");
			}else{
				
				//减库存成功  记录购买行为
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				//唯一验证：seckillId,userphone
				if(insertCount <= 0){
					
					//重复秒杀
					throw new RepeatKillException("seckill repeated");
				}else{
					
					//秒杀成功
					Successkilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch(SeckillCloseExcepion e1){
			
			throw e1;
		}catch(RepeatKillException e2){
			
			throw e2;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
			//所有编译期异常转化为运行期异常
			throw new SeckillException("seckil inner error:"+ e.getMessage());
		}
	}

}
