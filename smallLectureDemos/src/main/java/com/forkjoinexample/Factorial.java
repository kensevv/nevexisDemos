package com.forkjoinexample;

import java.math.BigDecimal;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Factorial extends RecursiveTask<BigDecimal> {
	private static final long serialVersionUID = 1L;

	private long start;
	private long end;
	private long optimalSplits;

	public Factorial(long start, long end, long optimalSplits) {
		this.start = start;
		this.end = end;
		this.optimalSplits = optimalSplits;
	}

	@Override
	protected BigDecimal compute() {
		if (end - start <= optimalSplits) {
			return LongStream.rangeClosed(start, end).mapToObj(BigDecimal::valueOf).reduce(BigDecimal.ONE,
					(multiplyTotal, currElem) -> multiplyTotal.multiply(currElem));
		}
		return Stream.of(new Factorial(start, getMiddle(), optimalSplits),
				new Factorial(getMiddle() + 1, end, optimalSplits)).map(fact -> {
					return fact.fork();
				}).map(forked -> forked.join())
				.reduce(BigDecimal.ONE, (multiplyTotal, currElem) -> multiplyTotal.multiply(currElem));

		// Factorial left = new Factorial(start, getMiddle(), treshold);
		// Factorial right = new Factorial(getMiddle() + 1, end, treshold);
		// left.fork();
		// right.fork();
		// return left.join().multiply(right.join());
	}

	private long getMiddle() {
		return (start + end) / 2;
	}

	public static BigDecimal getFactorialResult(long factorial, long treshold) {
		return ForkJoinPool.commonPool().invoke(new Factorial(1, factorial, treshold));
	}

	public static void main(String[] args) {
		System.out.println("Minimum time : " + LongStream.rangeClosed(2, 200).map(optimalSplits -> {
			System.out.println("Optimal Splits : " + optimalSplits);
			long timeinMs = System.nanoTime();
			System.out.println("RESULT : " + Factorial.getFactorialResult(1000, optimalSplits));
			timeinMs = System.nanoTime() - timeinMs;
			System.out.println("TIME : " + timeinMs);
			return timeinMs;
		}).min().getAsLong());
	}
}
