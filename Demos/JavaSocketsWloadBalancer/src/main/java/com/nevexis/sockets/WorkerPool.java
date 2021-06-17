package com.nevexis.sockets;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerPool {
	private static final int THREAD_COUNT = 10;
	private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

	public static ExecutorService getExecutorService() {
		return executorService;
	}

	public static int getThreadCount() {
		return THREAD_COUNT;
	}
}