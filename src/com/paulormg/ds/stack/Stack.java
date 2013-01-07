package com.paulormg.ds.stack;

public interface Stack<T> {

	public void push(T elem);
	
	public T pop();
	
	public boolean isEmpty();
	
	public int size();
}