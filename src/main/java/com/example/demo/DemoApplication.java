package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DesignServiceImpl;

@SpringBootApplication
@EnableScheduling
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private DesignServiceImpl designServiceImpl;

	@ResponseBody
	@RequestMapping(value = "/payfor", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String payfor(@RequestParam String orderNo) throws Exception {
		System.out.println("payfor-：" + orderNo);
		String res = designServiceImpl.execPayforOrder(orderNo);
		if ("success".equals(res)) {
			return "OK";
		}
		return "finish";
	}

}
