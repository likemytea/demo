package com.example.demo.service;

import com.example.demo.service.iface.WalIFace;

public abstract class AbstractPayforStrategyServiceImpl implements WalIFace {

	@Override
	public void execTask() {
		/** 执行业务前先写入WAL表 */
		writeWAL();
		/** 调用第三方服务 */
		callThirdParty();
		/** 各自的业务逻辑 */
		doMyTask();
	}

	/**
	 * 执行记账
	 */
	private void writeWAL() {
		System.out.println("执行模板策略-执行记账");
	}

	/**
	 * 调用第三方服务
	 */
	public abstract void callThirdParty();

	/**
	 * 执行业务逻辑
	 */
	public abstract void doMyTask();
}
