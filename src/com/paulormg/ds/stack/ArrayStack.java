package com.paulormg.ds.stack;

import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {

	private T[] elements;
	private int N;

	private static final int DEFAULT_CAPACITY = 1;

	public ArrayStack(){
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayStack(int initialCapacity){
		this.elements = (T[]) new Object[initialCapacity];
		this.N = 0;
	}

	public void push(T elem) {
		elements[N++] = elem;
		
		if(N == elements.length){
			resize(elements.length*2);
		}
	}

	public int size(){
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}	
	
	public T pop(){
		if(N == 0) throw new NoSuchElementException();
		
		if(N <= elements.length/4){
			resize(elements.length/2);
		}
		return elements[--N];
	}

	protected int getCapacity(){
		return elements.length;
	}
	
	private void resize(int newCapacity){
		assert newCapacity >= N;
		T[] newElements = (T[]) new Object[newCapacity];
		System.arraycopy(elements, 0, newElements, 0, N);
		this.elements = newElements;
	}
}