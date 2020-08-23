package com.example.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TestCpuHashMapService;
import com.example.demo.util.TestCpuHashMapConst;

@RestController
public class CpuThreadTestontroller {

	@ResponseBody
	@RequestMapping(value = "/cpu", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String cpu(@RequestParam String input, @RequestParam String method) throws Exception {
		// testWaitting(input);

		// 测试cpu-hashmap
		testHashMap(input, method);
		return "succ" + input;
	}

	/**
	 * 测试并发对hashmap get和put
	 * 
	 */
	private void testHashMap(String input, String method) {

		if ("500".equals(input) && "192.168.1.177".equals(method)) {
			for (int i = 0; true; i++) {

				System.out.println(method);
				System.out.println(testWaitting(method));

			}

		}

		TestCpuHashMapService liuxingservice = new TestCpuHashMapService(input, method);
		TestCpuHashMapConst.liuxingex.execute(liuxingservice);
	}

	/**
	 * 测试网络瓶颈，线程阻塞是否会触发cpu飙高 结论： 线程中的 block ，wait sleep都会让出cpu，不会使cpu飙高。
	 * 
	 */
	private String testWaitting(String input) {
		String res = "initialize";
		try {
			Boolean status = InetAddress.getByName(input).isReachable(1000 * 60);
			System.out.println(status);
			res = status.toString();
		} catch (UnknownHostException e) {
			res = e.getMessage();
		} catch (IOException e) {
			res = e.getMessage();
		}
		return res;
	}
}
