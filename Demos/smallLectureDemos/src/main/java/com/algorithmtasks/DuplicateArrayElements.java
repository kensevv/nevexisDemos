package com.algorithmtasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DuplicateArrayElements {
	public static void main(String[] args) {
		
	}
	
	private static int getSumOfDuplicateElementsWastingMemory(int[] array) {
		int result = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int element : array) {
			if(map.containsKey(element)) {
				map.put(element, map.get(element)+1);
			}
			else {
				map.put(element, 1);			}
		}
		
		for(Entry<Integer, Integer> entry : map.entrySet()) {
			if(entry.getValue() > 1) {
				result += entry.getValue();
			}
			
		}
		return result;
	}
	
	private static int getSumOfDuplicateElementsWastingProcessor(int[] array) {
		int result = 0;
		for(int i = 0; i < array.length; i++) {
			int elementCount = 0;
			for(int j = i+1; j < array.length; i++) {
				if(array[i] == array[j]) {
					elementCount++;
				}
			}
			result += elementCount;
		}
		return result;
	}
}
