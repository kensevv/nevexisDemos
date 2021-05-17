package com.reporting.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component()
public class ThreadPool {
	private final int THREAD_COUNT = 8;
	private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public int getThreadCount() {
		return THREAD_COUNT;
	}
}
