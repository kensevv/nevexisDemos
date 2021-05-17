package com.locks;

public class A {
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	public void modifyName() {
		synchronized (lock1) {
			Person.name = "NEW NAME";
		}
	}
	
	public void modifyAge() {
		synchronized (lock2) {
			Person.age = 5;
		}
	}
	
	public static void main(String[] args) { 
		
	}
}
