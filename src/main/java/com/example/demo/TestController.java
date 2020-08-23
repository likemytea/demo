// package com.example.demo;
//
// import java.util.concurrent.TimeUnit;
//
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.example.demo.schedule.MyScheduler;
//
// @RestController
// public class TestController {
//
// @ResponseBody
// @RequestMapping(value = "/doTest", method = RequestMethod.GET)
// public String doTest(@RequestParam String sender) {
// String res = "normal";
//
// try {
// if (MyScheduler.mftLock.tryLock(2, TimeUnit.SECONDS)) {
// res = "get lock sucess";
// } else {
// res = "get lock failure";
// }
//
// } catch (InterruptedException e) {
// e.printStackTrace();
// } finally {
// if (MyScheduler.mftLock.isHeldByCurrentThread()) {
// MyScheduler.mftLock.unlock();
// }
// }
//
// return res;
// }
// }
