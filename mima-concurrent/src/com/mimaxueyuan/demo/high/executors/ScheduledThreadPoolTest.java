package com.mimaxueyuan.demo.high.executors;

import com.mimaxueyuan.ScheduledThreadPool;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @author: Kevin
 * @官网: 	www.mimaxueyuan.com
 * @Q Q群:	660567408
 * @Email:	mimaxueyuan@163.com
 * [每天进步一点点、人生带来大改变...]
 * [本代码对应视频地址:http://study.163.com/course/introduction/1004176043.htm]
 */
public class ScheduledThreadPoolTest {

	public static void test1() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			Runnable task = new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + ">> delay " + index + " seconds run....");
				}
			};

			ScheduledFuture<?> schedule = scheduledThreadPool.schedule(task, i, TimeUnit.SECONDS);
		}
		scheduledThreadPool.shutdown();
	}

	public static void test2() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(12);
		Runnable task = new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ">> sleep..." + System.currentTimeMillis());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ">> run....." + System.currentTimeMillis());
			}
		};
		ScheduledFuture<?> scheduleAtFixedRate = scheduledThreadPool.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
		scheduleAtFixedRate.cancel(false);
	}

	public static void test3() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
		Runnable task = new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ">> sleep..." + System.currentTimeMillis());
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ">> run....." + System.currentTimeMillis());
			}
		};
		ScheduledFuture<?> scheduleWithFixedDelay = scheduledThreadPool.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduleWithFixedDelay.cancel(true);
		scheduledThreadPool.shutdown();
	}
	private static void test4() {

//        Stream.iterate(0, x-> x +1).limit(5).forEach(x -> {
//        ExecutorService instance = Executors.newFixedThreadPool(30);

        /*for (int i = 0; i < 10; i++) {
            instance.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("y");
                }
            });
        }*/
            Stream.iterate(0, y-> y +1).limit(3).forEach(y -> {
                ScheduledExecutorService instance = ScheduledThreadPool.getInstance();
                ScheduledFuture<?> schedule = instance.schedule(() -> System.out.println("y" + y), 2, TimeUnit.SECONDS);
                schedule.cancel(true);
            });
//        instance.shutdown();

//        });

    }
	public static void main(String[] args) {
		 //test1();
		 //test2();
		 //test3();
        test4();
	}
}
