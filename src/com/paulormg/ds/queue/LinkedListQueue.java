package com.paulormg.ds.queue;

import java.util.NoSuchElementException;

public class LinkedListQueue<T> implements Queue<T> {

	private class Node {
		T elem;
		Node next;

		public Node(T elem){
			this.elem = elem;
			this.next = null;
		}
	}

	private Node head;
	private Node tail;
	private int N;
	
	public LinkedListQueue(){
		N = 0;
		this.head = null;
		this.tail = null;
	}

	public void enqueue(T elem){
		Node oldTail = tail;
		tail = new Node(elem);

		if (isEmpty()){
			head = tail;
		} else {
			oldTail.next = tail;
		}
		N++;
	}

	public T dequeue(){
		if (N == 0) throw new NoSuchElementException();
		
		T result = null;
		if(!isEmpty()){
			result = head.elem;
			head = head.next;
			if (head == null){
				tail = null;
			}
			N--;		
		}
		return result;
	}

	public boolean isEmpty(){
		return head == null;
	}
	
	public int size(){
		return this.N;
	}
}