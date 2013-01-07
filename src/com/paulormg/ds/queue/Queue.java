package com.paulormg.ds.queue;

public interface Queue<E> {
	
	public void enqueue(E elem);

	public E dequeue();
	
	public boolean isEmpty();	
	
	public int size();
}