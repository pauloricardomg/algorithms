package com.paulormg.algorithms.fibonacci;

import java.util.Arrays;

public class Fibonacci {

	public static long fibonacciDP(int n){
		if (n < 2) return n;
		
		long[] dp = new long[n+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		dp[1] = 1;
		
		return fib(n, dp);
	}

	public static long fibonacciIterative(int n){
		if (n < 2) return n;
		
		int a = 0;
		int b = 1;
		int c = 0;
		
		for (int i = 2; i <= n; i++){
			c = a + b;
			a = b;
			b = c;
		}
		
		return c;
	}
	
	private static long fib(int n, long[] dp){
		if (dp[n] != -1) return dp[n];
		
		return fib(n-1, dp) + fib(n-2, dp);
	}

	
	public static void main ( String[] args ) {
		System.out.println("Recursive:");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print ( fibonacciDP(i) + ", " );
        }
        System.out.println ( fibonacciDP(10) );
        
        
        System.out.println("Iterative:");
        for ( int i = 0; i < 10; i++ ) {
            System.out.print ( fibonacciIterative(i) + ", " );
        }
        System.out.println ( fibonacciIterative(10) );
    }
}