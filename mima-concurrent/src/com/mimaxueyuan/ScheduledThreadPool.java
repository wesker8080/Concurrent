package com.mimaxueyuan;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author MR.ZHANG
 * @create 2019-01-10 16:09
 */
public class ScheduledThreadPool {
    private ScheduledThreadPool(){}
    private static class Holder {
        private static ScheduledExecutorService INSTANCE = Executors.newScheduledThreadPool(10);
    }
    public static ScheduledExecutorService getInstance() {
        return Holder.INSTANCE;
    }
}
