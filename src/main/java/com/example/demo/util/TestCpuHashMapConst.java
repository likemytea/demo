package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCpuHashMapConst {
	public static long liuxingid = 0;
	public static Map<Long, Long> liuxingmap = new HashMap<Long, Long>();
	public static ExecutorService liuxingex = Executors.newCachedThreadPool();
}
