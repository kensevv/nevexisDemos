package com.algorithmtasks;

public class TokenGame {
	public static int getGameResult(int n, int k) {
		return getGameResultHelper(n, k, 0);
	}

	private static int getGameResultHelper(int n, int k, int counter) {
		if (n == 1) {
			return counter;
		}
		if (k < 1 || n % 2 != 0) {
			return getGameResultHelper(n - 1, k, counter + 1);
		} else {
			return Math.min(getGameResultHelper(n - 1, k, counter + 1), getGameResultHelper(n / 2, k - 1, counter + 1));
		}
	}

	public static void main(String[] args) {
		System.out.println(getGameResult(6, 1));
	}
}
