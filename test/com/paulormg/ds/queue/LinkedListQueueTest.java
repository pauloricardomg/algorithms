package com.paulormg.ds.queue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedListQueueTest {

	@Test
	public void testQueue() {
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
		try {
			queue.dequeue();
			fail("Should have thrown exception");
		} catch (NoSuchElementException exc){
			// success!
		}
		
		queue.enqueue(0);
		assertFalse(queue.isEmpty());
		assertEquals(1, queue.size());

		assertEquals(new Integer(0), queue.dequeue());
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
		try {
			queue.dequeue();
			fail("Should have thrown exception");
		} catch (NoSuchElementException exc){
			// success!
		}		
		
		queue.enqueue(0);
		queue.enqueue(1);
		assertFalse(queue.isEmpty());
		assertEquals(2, queue.size());
		
		assertEquals(new Integer(0), queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(new Integer(1), queue.dequeue());
		assertEquals(0, queue.size());
	}

}
