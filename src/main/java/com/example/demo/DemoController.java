package com.example.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.DESUtil;

@RestController
public class DemoController {

	@ResponseBody
	@RequestMapping(value = "/jiami", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addUser(@RequestParam String sender, @RequestParam String x) throws Exception {
		System.out.println("传过来未经处理的-：" + x);
		// x = jiahao(x);
		x = jiemi(x);
		return "finish";
	}

	private String jiahao(String x) {
		x = x.replace("XX1XX", "+");
		System.out.println("替换加号后的-" + x);
		return x;
	}

	private String jiemi(String x) {
		try {
			x = DESUtil.decryption(x, "6$%^bb!1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 密钥设置为8个字节
		System.out.println("解密后" + x);
		return x;
	}

	private String jiema(String x) {
		try {
			x = java.net.URLDecoder.decode(x, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("解码码码码后的-" + x);
		return x;
	}

	@ResponseBody
	@RequestMapping(value = "/doTest", method = RequestMethod.GET)
	public String doTest(@RequestParam String sender) {
		System.out.println("1111");

		Map mp = new HashMap();
		mp.put("sender", "hahaha");
		getWithParams("http://localhost:8080/doTest2", mp);
		return "111";
	}

	public static void main(String[] args) {
		Map mp = new HashMap();
		mp.put("sender", "hahaha");
		getWithParams("http://localhost:8080/doTest2", mp);

	}

	@RequestMapping(value = "/doTest2", method = RequestMethod.GET)
	public String doTest2(@RequestParam String sender) {
		System.out.println("2222" + "");
		return "2222";
	}

	private static void getWithParams(String url, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		CloseableHttpResponse response = null;
		try {
			// 创建Get请求
			url = joinParam(url, params);
			HttpGet httpGet = new HttpGet(url);
			httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
			System.out.println("请求中设置的协议版本" + httpGet.getProtocolVersion());

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000) // 服务器响应超时时间
					.setConnectTimeout(2000) // 连接服务器超时时间
					.build();
			httpGet.setConfig(requestConfig);
			// http header keepalive的设置方式
			httpGet.setHeader("Connection", "close");
			/*
			 * 当服务方是http:1.1协议的情况下
			 **********************************************************/
			// 请求方设置协议为 1.1时，header里边需要设置close或者包含close的字符串，服务方能接收到 Connection:close,设置其它
			// 任何字符串或者不设置，服务方不会接收到 "Connection:xxx" 这样的字符串.
			// 请求方设置协议为 1.0时,header里边需要设置keep-alive或者包含keep-alive的字符串或者不设置，服务方能接收到
			// Connection:keep-alive,设置任何其它字符串，都是Connection:close
			/***********************************************************/

			// 由客户端执行(发送)Get请求
			/*********************
			 * 调用http服务
			 *********************************************************/
			response = httpClient.execute(httpGet);

			// 获取所有头信息
			Header[] allHeaders = response.getAllHeaders();
			System.out.println("----响应头信息-------------------------------start");
			for (Header allHeader : allHeaders) {
				// System.out.println(allHeader.getName());
				// System.out.println(allHeader.getValue());
				System.out.println(allHeader.toString());
			}
			System.out.println("----响应头信息---------------------------------end");
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应中获取的协议版本:" + response.getProtocolVersion());
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String joinParam(String url, Map<String, Object> params) {
		if (params == null || params.size() == 0) {
			return url;
		}

		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?");

		int counter = 0;
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (key == null) {
				continue;
			}

			if (counter == 0) {
				urlBuilder.append(key).append("=").append(value);
			} else {
				urlBuilder.append("&").append(key).append("=").append(value);
			}
			counter++;
		}

		return urlBuilder.toString();
	}
}
