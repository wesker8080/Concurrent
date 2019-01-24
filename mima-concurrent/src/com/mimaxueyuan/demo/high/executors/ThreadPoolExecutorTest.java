package com.mimaxueyuan.demo.high.executors;

import java.util.concurrent.*;

/**
 * @author: Kevin
 * @官网: 	www.mimaxueyuan.com
 * @Q Q群:	660567408
 * @Email:	mimaxueyuan@163.com
 * [每天进步一点点、人生带来大改变...]
 * [本代码对应视频地址:http://study.163.com/course/introduction/1004176043.htm]
 */
public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		
		//AbortPolicy			抛出异常,不影响其他线程运行
		//CallerRunsPolicy		调用当前任务
		//DiscardOldestPolicy	丢弃最老的任务
		//DiscardPolicy			直接丢弃,什么也不做
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,0,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1),new ThreadPoolExecutor.AbortPolicy());
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,0,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1),new CallerRunsPolicy());
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,0,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1),new DiscardOldestPolicy());
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,0,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1),new DiscardPolicy());
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(3,10,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(5),r -> new Thread(r, "wesker_" + r.hashCode()),new MyPolicy());
		
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 7, 60L,
//				TimeUnit.SECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r,"wesker_" + r.hashCode()));
		for(int i=0;i<20;i++){
			MyTask task = new MyTask(i);
			executor.submit(task);
		}
		
		executor.shutdown();
	}
}

class MyPolicy implements RejectedExecutionHandler{
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(r+"被拒绝执行"+System.currentTimeMillis());
	}
}

class MyTask implements Runnable{

	int index = 0;
	
	public MyTask(int index) {
		this.index = index;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+">>run "+index);
	}
	
}
