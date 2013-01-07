package com.paulormg.ds.stack;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedListStackTest {

	@Test
	public void testStack() {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
		try {
			stack.pop();
			fail("Should have thrown exception");
		} catch (NoSuchElementException exc){
			// success!
		}
		
		stack.push(0);
		assertFalse(stack.isEmpty());
		assertEquals(1, stack.size());

		assertEquals(new Integer(0), stack.pop());
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
		try {
			stack.pop();
			fail("Should have thrown exception");
		} catch (NoSuchElementException exc){
			// success!
		}		
		
		stack.push(0);
		stack.push(1);
		assertFalse(stack.isEmpty());
		assertEquals(2, stack.size());
		
		assertEquals(new Integer(1), stack.pop());
		assertEquals(1, stack.size());
		assertEquals(new Integer(0), stack.pop());
		assertEquals(0, stack.size());
	}

}
