package com.paulormg.algorithms.fibonacci;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FibonacciTest {

	@Test(timeout=1000)
	public void testIterative() {
		assertEquals(0, Fibonacci.fibonacciIterative(0));
		assertEquals(1, Fibonacci.fibonacciIterative(1));
		assertEquals(1, Fibonacci.fibonacciIterative(2));
		assertEquals(2, Fibonacci.fibonacciIterative(3));
		assertEquals(2, Fibonacci.fibonacciIterative(3));
		
		assertEquals(3524578, Fibonacci.fibonacciIterative(33));
		assertEquals(5702887, Fibonacci.fibonacciIterative(34));
	}
	
	@Test(timeout=1000)
	public void testDP() {
		assertEquals(0, Fibonacci.fibonacciDP(0));
		assertEquals(1, Fibonacci.fibonacciDP(1));
		assertEquals(1, Fibonacci.fibonacciDP(2));
		assertEquals(2, Fibonacci.fibonacciDP(3));
		assertEquals(2, Fibonacci.fibonacciDP(3));
		
		assertEquals(3524578, Fibonacci.fibonacciDP(33));
		assertEquals(5702887, Fibonacci.fibonacciDP(34));
	}

}
