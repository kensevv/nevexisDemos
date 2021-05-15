package com.reporting.threads;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.reporting.entities.Persons;
import com.reporting.services.DBService;

public class FetchPageThread extends Thread {
	private DBService dbService;
	private CyclicBarrier barrier;
	private int limit;
	private int offset;
	private Persons[] pageToFill;

	public FetchPageThread(DBService dbService, CyclicBarrier barrier, int limit, int offset,
			Persons[] pageToFill) {
		this.dbService = dbService;
		this.barrier = barrier;
		this.limit = limit;
		this.offset = offset;
		this.pageToFill = pageToFill;
	}

	@Override
	public void run() {
		List<Persons> currentPage = dbService.getPersons(limit, offset);

		if (null != currentPage && 0 != currentPage.size() && offset < pageToFill.length) {
			int currentIndex = offset;
			for (int i = 0; i < currentPage.size(); i++) {
				pageToFill[currentIndex] = currentPage.get(i);
				currentIndex++;
			}
		}

		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
