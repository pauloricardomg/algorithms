package com.paulormg.algorithms.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReverseStringTest {

	@Test
	public void testReverse() {
		String string = "";
		assertEquals("", ReverseString.reverse(string));
		
		string = "a";
		assertEquals("a", ReverseString.reverse(string));
		
		string = "banana";
		assertEquals("ananab", ReverseString.reverse(string));
		
		string = "ab";
		assertEquals("ba", ReverseString.reverse(string));
		
		string = "abc";
		assertEquals("cba", ReverseString.reverse(string));
		
		string ="abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz";
		assertEquals("zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba", 
				ReverseString.reverse(string));	
	}
	
	@Test
	public void testReverse2() {
		String string = "";
		assertEquals("", ReverseString.reverse2(string));
		
		string = "a";
		assertEquals("a", ReverseString.reverse2(string));
		
		string = "banana";
		assertEquals("ananab", ReverseString.reverse2(string));
		
		string = "ab";
		assertEquals("ba", ReverseString.reverse2(string));
		
		string = "abc";
		assertEquals("cba", ReverseString.reverse2(string));
		
		string ="abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz" +
				"abcdefghijklmnopqrstuvwxyz";
		assertEquals("zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba" +
				"zyxwvutsrqponmlkjihgfedcba", 
				ReverseString.reverse2(string));	
	}

}
