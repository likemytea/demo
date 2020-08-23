// package com.example.demo.schedule;
//
// import java.util.Date;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.locks.ReentrantLock;
//
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// @Component
// public class MyScheduler {
// public static ReentrantLock mftLock = new ReentrantLock();
//
// @Scheduled(cron = "0/10 * * * * ?")
// public void doTest() throws InterruptedException {
// /*** 加锁 ******************************************/
// mftLock.lock();
// try {
// System.out.println("开始执行MFT文件下载业务");
// TimeUnit.SECONDS.sleep(7);
//
// System.out.println("The time is now {}" + new Date());
// } finally {
// /*** 释放锁 ******************************************/
// mftLock.unlock();
// }
// System.out.println("持锁结束");
// }
// }
