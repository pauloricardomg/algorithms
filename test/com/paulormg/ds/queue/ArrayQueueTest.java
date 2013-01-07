package com.paulormg.ds.queue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ArrayQueueTest {

	@Test
	public void testQueue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(1);
		
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
		assertEquals(1, queue.getCapacity());
		try {
			queue.dequeue();
			fail("Should have thrown exception");
		} catch (NoSuchElementException exc){
			// success!
		}
		
		queue.enqueue(0);
		assertFalse(queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals(2, queue.getCapacity());

		assertEquals(new Integer(0), queue.dequeue());
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());
		assertEquals(2, queue.getCapacity());
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
		assertEquals(4, queue.getCapacity());
		
		assertEquals(new Integer(0), queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(4, queue.getCapacity());
		assertEquals(new Integer(1), queue.dequeue());
		assertEquals(0, queue.size());
		assertEquals(2, queue.getCapacity());
	}

}
