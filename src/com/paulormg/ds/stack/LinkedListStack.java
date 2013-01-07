package com.paulormg.ds.stack;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
	
	private class Node {
		T elem;
		Node next;

		public Node(T elem, Node next){
			this.elem = elem;
			this.next = next;
		}
	}

	private Node head;
	private int N;

	public LinkedListStack(){
		this.head = null;
		this.N = 0;
	}

	@Override
	public void push(T elem){
		head = new Node(elem, head);
		N++;
	}

	@Override
	public T pop(){
		if(N == 0) throw new NoSuchElementException();		
		
		T result = null;
		if(!isEmpty()){
			result = head.elem;
			head = head.next;
			N--;
		}
		
		return result;
	}
	
	@Override
	public boolean isEmpty() {
		return this.head == null;
	}

	@Override
	public int size() {
		return N;
	}
}