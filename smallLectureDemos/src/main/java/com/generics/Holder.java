package com.generics;

public class Holder<T> {
	private T a;

	public Holder(T a) {
		this.a = a;
	}

	public void set(T a) {
		this.a = a;
	}

	public T get() {
		return a;
	}

	public static void main(String[] args) {
		Holder<Integer> h = new Holder<Integer>(1);
		Integer a = h.get(); // No cast needed
		// h3.set("Not an Automobile"); // Error
		// h3.set(1); // Error
	}
}