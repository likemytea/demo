package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service("designService")
public class DesignServiceImpl extends AbstractPayforStrategyServiceImpl {
	/**
	 * 支付订单服务实现类
	 * 
	 * @param input
	 *            入参
	 * @param strategy
	 *            使用的策略
	 */
	public String execPayforOrder(String input) {
		// 执行父类模板方法
		this.execTask();
		return input;
	}

	@Override
	public void callThirdParty() {
		System.out.println("执行本地差异化方法-调用第三方服务");

	}

	@Override
	public void doMyTask() {
		System.out.println("执行本地差异化方法-执行自己的业务");

	}
}
