package com.forkjoinexample;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Sum extends RecursiveTask<Long> {
	private static final long serialVersionUID = 1L;
	
	static final int optimalElementCount = 10;

    int start;
    int end;
    List<Long> array;

    Sum(List<Long> arr, int start, int end) {
        this.array = arr;
        this.start   = start;
        this.end  = end;
    }

    protected Long compute() {
        if(end - start <= optimalElementCount) {
            long sum = 0;
            for(int i=start; i < end; ++i) 
                sum += array.get(i);
            return sum;
         } else {
            int mid = start + (end - start) / 2;
            Sum left  = new Sum(array, start, mid);
            Sum right = new Sum(array, mid, end);
            left.fork(); 
            right.fork();
            return left.join() + right.join();
         }
     }

     static long sumArray(List<Long> array) {
         return ForkJoinPool.commonPool().invoke(new Sum(array,0,array.size()));
     }
     
     public static void main(String[] args)
     {
    	 List<Long> list = LongStream.range(0, 100).boxed().collect(Collectors.toList());
    	 System.out.println(Sum.sumArray(list));
     }
}