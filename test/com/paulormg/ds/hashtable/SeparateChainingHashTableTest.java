package com.paulormg.ds.hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.paulormg.ds.hashtable.SeparateChainingHashTable;

public class SeparateChainingHashTableTest {

	@Test
	public void testPutGetContainsAndSize() {
		final int CAPACITY = 5;
		SeparateChainingHashTable<Integer, String> table = new SeparateChainingHashTable<Integer, String>(CAPACITY);
		
		assertEquals(0, table.size());
		assertFalse(table.contains(0));
		assertNull(table.get(0));
		assertEquals(CAPACITY, table.getCapacity());
	
		table.put(0, "zero");
		assertEquals(1, table.size());
		assertTrue(table.contains(0));
		assertEquals("zero", table.get(0));	
		assertEquals(CAPACITY, table.getCapacity());
		
		table.put(Integer.MIN_VALUE, "min infinity");
		assertEquals(2, table.size());
		assertTrue(table.contains(Integer.MIN_VALUE));
		assertEquals("min infinity", table.get(Integer.MIN_VALUE));	
		assertEquals(CAPACITY, table.getCapacity());	
		assertEquals("zero", table.get(0));	
		
		table.put(Integer.MAX_VALUE, "max infinity");
		assertEquals(3, table.size());
		assertTrue(table.contains(Integer.MAX_VALUE));
		assertEquals("max infinity", table.get(Integer.MAX_VALUE));	
		assertEquals(CAPACITY, table.getCapacity());
		assertEquals("zero", table.get(0));	
		
		table.put(-3, "minus three");
		table.put(10, "ten");
		assertEquals(5, table.size());
		assertEquals(CAPACITY*2, table.getCapacity());
		assertEquals("zero", table.get(0));	
		assertEquals("min infinity", table.get(Integer.MIN_VALUE));	
		assertEquals("max infinity", table.get(Integer.MAX_VALUE));	
		assertEquals("minus three", table.get(-3));	
		assertEquals("ten", table.get(10));	
	}

	@Test
	public void testRemove() {
		final int CAPACITY = 1;
		SeparateChainingHashTable<Integer, String> table = new SeparateChainingHashTable<Integer, String>(CAPACITY);
		
		assertNull(table.remove(0));
		assertEquals(0, table.size());
		assertEquals(CAPACITY, table.getCapacity());

		table.put(0, "zero");
		assertEquals(CAPACITY*2, table.getCapacity());
		assertEquals("zero", table.remove(0));
		assertEquals(0, table.size());
		assertEquals(CAPACITY*2, table.getCapacity());

		table.put(0, "zero");
		table.put(Integer.MIN_VALUE, "min infinity");
		table.put(Integer.MAX_VALUE, "max infinity");
		assertEquals(3, table.size());
		assertEquals(CAPACITY*4, table.getCapacity());
		assertEquals("zero", table.remove(0));
		assertEquals("min infinity", table.get(Integer.MIN_VALUE));	
		assertEquals("max infinity", table.get(Integer.MAX_VALUE));	
		assertEquals("min infinity", table.remove(Integer.MIN_VALUE));	
		assertEquals("max infinity", table.remove(Integer.MAX_VALUE));	
		assertEquals(CAPACITY*2, table.getCapacity());
		assertNull(table.remove(0));
		assertEquals(0, table.size());
	}
}
