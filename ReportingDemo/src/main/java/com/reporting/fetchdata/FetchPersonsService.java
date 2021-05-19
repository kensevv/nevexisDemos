package com.reporting.fetchdata;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reporting.entities.Persons;
import com.reporting.services.DBService;
import com.reporting.threadpool.ThreadPool;

@Service
public class FetchPersonsService {
	@Autowired
	private DBService dbService;
	@Autowired
	private ThreadPool threadPool;
	private long objectsCount;
	private int limitObjectsPerPage;
	private int offset;
	
	private Persons[] allPersons;
	
	@PostConstruct
	public void postConstruct() throws InterruptedException, BrokenBarrierException {
		doCalculations();
		fetchObjects();
	}
	
	private void fetchObjects() throws InterruptedException, BrokenBarrierException {
		allPersons = new Persons[(int) objectsCount];
		CyclicBarrier barrier = new CyclicBarrier(threadPool.getThreadCount() + 1);

		for (int i = 0; i < threadPool.getThreadCount(); i++) {
			threadPool.getExecutorService()
					.submit(new FetchPageThread(dbService, barrier, limitObjectsPerPage, offset, allPersons));
			offset += limitObjectsPerPage;
		}
		barrier.await();
	}
	
	private void doCalculations() {
		objectsCount = dbService.getPersonsCount();
		limitObjectsPerPage = (int) Math.ceil((double) objectsCount / (double) threadPool.getThreadCount());
		offset = 0;
	}
	
	public Persons[] getAllPersons() {
		return this.allPersons;
	}
}
