package com.paulormg.ds.queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E>  {

	private E[] elements;
	private int N;
	private int head;
	private int tail;

	private static final int DEFAULT_CAPACITY = 1;

	public ArrayQueue(){
		this(DEFAULT_CAPACITY);
	}

	public ArrayQueue(int capacity){
		head = 0;
		tail = 0;
		this.N = 0;
		this.elements = (E[]) new Object[capacity];
	}
	
	/*
	 * Implements Queue<E> 
	 */
	
	public void enqueue(E elem){
		elements[tail] = elem;
		tail = (tail+1)%elements.length;
		N++;
		
		// Grow array
		if (N == elements.length){
			resize(elements.length*2);
		}
	}

	public E dequeue(){
		if (N == 0) throw new NoSuchElementException();
		
		E toReturn = elements[head];
		elements[head] = null;
		head = (head+1)%elements.length;
		N--;
		
		// Shrink array
		if (N < elements.length/4){
			resize(elements.length/2);
		}
		
		return toReturn;
	}

	public boolean isEmpty(){
		return N == 0;
	}

	public int size() {
		return N;
	}
	
	/*
	 * UTIL
	 */
	
	private void resize(int newCapacity){
		E[] newElems = (E[]) new Object[newCapacity];
		for(int i=0; i<N; i++){
			newElems[i] = elements[(head + i) % elements.length];
		}
		this.head = 0;
		this.tail = N;
		this.elements = newElems;
	}
	
	protected int getCapacity(){
		return elements.length;
	}
}