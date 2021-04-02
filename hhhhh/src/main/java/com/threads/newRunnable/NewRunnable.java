package com.threads.newRunnable;

public class NewRunnable {

	static  long number = 0;

	synchronized static void xNumber(int a) {
		number = number + a;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 10000; i++) {
					xNumber(1);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Runnable Thread: run is over");
			}

		});
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 10000; i++) {
					xNumber(1);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Runnable Thread 2: run is over");
			}

		});
		t1.start();
		t2.start();
		while (t1.isAlive() || t2.isAlive()) {

		}
		System.out.println(number);
		System.out.println("Main over!");
	}
}