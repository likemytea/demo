package com.example.demo.service;

import java.util.Random;

import com.example.demo.util.TestCpuHashMapConst;

public class TestCpuHashMapService implements Runnable {
	private String input = null;
	private String method = null;

	public TestCpuHashMapService(String _input, String _method) {
		method = _method;
		input = _input;
	}

	/**
	 * 测试多线程对hashmap并发get和put,remove
	 * 
	 * 
	 */
	private String testCpuHashMapPut(String input) {
		TestCpuHashMapConst.liuxingid = Long.parseLong(input);
		TestCpuHashMapConst.liuxingmap.put(TestCpuHashMapConst.liuxingid, TestCpuHashMapConst.liuxingid);
		return TestCpuHashMapConst.liuxingid + "";
	}

	/**
	 * 测试多线程对hashmap并发get和put,remove
	 * 
	 * 
	 */
	private String testCpuHashMapRemove(String input) {
		TestCpuHashMapConst.liuxingid = Long.parseLong(input);
		TestCpuHashMapConst.liuxingmap.remove(TestCpuHashMapConst.liuxingid, TestCpuHashMapConst.liuxingid);
		return TestCpuHashMapConst.liuxingid + "";
	}

	/**
	 * 测试多线程对hashmap并发get和put,remove
	 * 
	 * 
	 */
	private String testCpuHashMapGet(String input) {
		TestCpuHashMapConst.liuxingid = Long.parseLong(input);
		return TestCpuHashMapConst.liuxingmap.get(TestCpuHashMapConst.liuxingid) + "";
	}

	@Override
	public void run() {
		String res = null;
		if ("1".equals(method)) {
			res = "put" + testCpuHashMapPut(input);
			System.out.println(res);
		} else if ("2".equals(method)) {
			res = "remove" + testCpuHashMapRemove(input);
			System.out.println(res);
		} else {
			res = "get" + testCpuHashMapGet(input);
			System.out.println(res);
		}
	}

	public static void main(String[] args) {

	}
}
