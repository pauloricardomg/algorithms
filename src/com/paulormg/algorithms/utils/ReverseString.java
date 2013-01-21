package com.paulormg.algorithms.utils;

public class ReverseString {

	public static String reverse(String string){
		StringBuilder result = new StringBuilder(string.length()); 
		// complexity: O(N) or O(1) (depends on array allocation complexity)
		
		for(int i = string.length()-1; i >= 0; i--){ // loop complexity: O(N)
			result.append(string.charAt(i));
		}
		
		return result.toString(); // complexity: O(N)
	}
	
	public static String reverse2(String string){
		char[] str = string.toCharArray(); // complexity: O(N)
		int len = str.length;
		
		for (int i = 0; i < len/2; i++){ // loop complexity: O(N/2)
			int j = len-1-i;
			char aux = str[i];
			str[i] = str[j];
			str[j] = aux;
		}
		
		return new String(str); // complexity: O(N)
	}

}