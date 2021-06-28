package com.generics;

import java.util.List;

public class MyGeneric<T> {
	private T x;

	MyGeneric(T x) {
		this.x = x;
	}

	public void print() {

		System.out.printf("I am : %s\n", x.getClass().getName());

	}

	public void compare(T y) {
		System.out.println(x.equals(y) ? "the objects are the same" : " the objects are not the same");
	}

	public static <Dupe> void compare(Dupe x, Dupe y) {
		System.out.println(x.equals(y) ? "the objects are the same" : " the objects are not the same");
	}

	public static <T> void printArray(List<T> list) {
		for (T t : list) {
			System.out.println(t);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyGeneric other = (MyGeneric) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		MyGeneric<String> g1 = new MyGeneric<>("asddd");
		MyGeneric.compare("ivan", "ivan");

//		List<String> l = new LinkedList<>();
//		l.add("test");
//		l.add("ese");
//		MyGeneric.printArray(l);
//		g1.print();
//		g1.compare(3);
	}
}