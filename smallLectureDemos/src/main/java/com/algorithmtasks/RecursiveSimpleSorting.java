package com.algorithmtasks;

public class RecursiveSimpleSorting {
	public static void main(String[] args) {
		int[] arr = {3,2,1,5,4};
		recursiveSorting(arr);
		for(int a : arr) {
			System.out.println(a);
		}
	}
	
	private static void recursiveSorting(int[] array) {
		recursiveSortingHelper(array, 0, 1);
	}
	
	private static void recursiveSortingHelper(int[] array, int i, int j) {
		if(j == array.length - 1) {
			j = 0;
			i++;
		}
		if(i == array.length) {
			return;
		}
		if(array[i] < array[j]) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		recursiveSortingHelper(array, i, j+1);
	}
}
