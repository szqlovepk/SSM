//存放主要交互逻辑js代码
//javascript模块化
//seckill.detail.init(params);
var seckill = {
		
		//封装秒杀相关的ajax的url
		URL:{
			
			
		},
		
		validatePhone:function(phone){
											//isNaN 判断是否是非数字
			if(phone && phone.length == 11 && !isNaN(phone)){
				
				return true;
			}else{
				
				return false;
			}
		},
		
		//详情页面秒杀地址
		detail:{
			
			init:function(params){
				
				//用户手机验证和登录  计时交互
				//规划我们的交互流程
				//在cookie中查找手机号
				var killPhone = $.cookie('killPhone');
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var seckillId = params['startTime'];
				//验证手机号
				if(!seckill.validatePhone(killPhone)){
					//绑定phone
					//控制输出
					var killPhoneModal = $('#killPhoneModal'); 
					killPhoneModal.modal({
						//显示弹出层
						show:true,
						//禁止位置关闭
						backdrop:false,
						//关闭键盘事件
						keyboard:false 
					});
					$('#killPhoneBtn').click(function(){
	
						var inputPhone = $('#killphoneKey').val();
						
						if(seckill.validatePhone(inputPhone)){
							//电话写入cookie
							$.cookie('killPhone',inputPhone,{expires:7,path:'/'})
							//刷新页面
							window.location.reload();
						}else{
						
						  $('#killphoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
						}
					});
				}
			}
		}
}