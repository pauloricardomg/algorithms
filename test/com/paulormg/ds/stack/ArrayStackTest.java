package com.paulormg.ds.stack;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ArrayStackTest {

	@Test
	public void testStack() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(1);
		
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
		assertEquals(1, stack.getCapacity());
		try {
			stack.pop();
			fail("Should have thrown exception");
		} catch (NoSuchElementException exc){
			// success!
		}
		
		stack.push(0);
		assertFalse(stack.isEmpty());
		assertEquals(1, stack.size());
		assertEquals(2, stack.getCapacity());

		assertEquals(new Integer(0), stack.pop());
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.size());
		assertEquals(2, stack.getCapacity());
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
		assertEquals(4, stack.getCapacity());
		
		assertEquals(new Integer(1), stack.pop());
		assertEquals(1, stack.size());
		assertEquals(4, stack.getCapacity());
		assertEquals(new Integer(0), stack.pop());
		assertEquals(0, stack.size());
		assertEquals(2, stack.getCapacity());
	}

}
