package com.mimaxueyuan.demo.high.base;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Kevin
 * @官网: 	www.mimaxueyuan.com
 * @Q Q群:	660567408
 * @Email:	mimaxueyuan@163.com
 * [每天进步一点点、人生带来大改变...]
 * [本代码对应视频地址:http://study.163.com/course/introduction/1004176043.htm]
 */
public class CountDownLatchTest1 {

	public static void main(String[] args) {

		final CountDownLatch countDownLatch = new CountDownLatch(3);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t2 start waitting for countDownLatch to zero");
					countDownLatch.await();
					System.out.println("t2 stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t3 start waitting for countDownLatch to zero");
					countDownLatch.await();
					System.out.println("t3 stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t3").start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("countDownLatch.countDown1");
					countDownLatch.countDown(); //3-1=2
					Thread.sleep(1000);
					System.out.println("countDownLatch.countDown2");
					countDownLatch.countDown(); //2-1=1
					Thread.sleep(1000);
					System.out.println("countDownLatch.countDown3");
					countDownLatch.countDown();	//1-1=0
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();

	}

}
