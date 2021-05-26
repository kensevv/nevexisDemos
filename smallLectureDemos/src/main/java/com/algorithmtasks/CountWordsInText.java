package com.algorithmtasks;

public class CountWordsInText {
	public static void main(String[] args) {
		System.out.println(numberWordsInLongestSentence("Kenan Yusein. Testva zadachata a?"));
	}

	private static int numberWordsInLongestSentence(String text) {
		String[] textSplitted = text.split("\\.|\\?|\\!");
		int maxWords = 0;
		for (String sentence : textSplitted) {
			int numberOfWords = sentence.split("\\w+").length;
			if (numberOfWords > maxWords) {
				maxWords = numberOfWords;
			}
		}
		return maxWords;
	}
}