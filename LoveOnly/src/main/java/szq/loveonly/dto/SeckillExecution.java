package szq.loveonly.dto;

import szq.loveonly.entity.Successkilled;
import szq.loveonly.enums.SeckillStateEnum;

public class SeckillExecution {

	//Id
	private long seckillId;
	
	//秒杀执行结果状态
	private int state;
	
	//状态表示
	private String stateInfo;
	
	//秒杀成功对象
	private Successkilled successkilled;

	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, Successkilled successkilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successkilled = successkilled;
	}

	
	
	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}



	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public Successkilled getSuccesskilled() {
		return successkilled;
	}

	public void setSuccesskilled(Successkilled successkilled) {
		this.successkilled = successkilled;
	}
	
	
	
	
}
