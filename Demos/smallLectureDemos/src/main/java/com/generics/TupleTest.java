package com.generics;

public class TupleTest {
	static ThreeTuple<Character, String, Integer> g() {
		return new ThreeTuple<Character, String, Integer>('A', "hi", 47);
	}

	public static void main(String[] args) {
		ThreeTuple<Character, String, Integer> test1 = g();
		System.out.println(test1);
		System.out.println(new ThreeTuple<String, Boolean, Integer>("test2 ", true, 5));
	}
}
