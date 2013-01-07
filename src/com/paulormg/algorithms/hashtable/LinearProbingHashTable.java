package com.paulormg.algorithms.hashtable;

public class LinearProbingHashTable<K, V> implements HashTable<K, V>{

	protected static final int DEFAULT_CAPACITY = 1;

	final class Entry {
		final K key;
		V value;

		public Entry(K key, V value){
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("(%s -> %s)", key.toString(), value.toString());
		}
	}

	private Entry[] container;
	private int N;
	private int minCapacity;

	@SuppressWarnings("unchecked")
	public LinearProbingHashTable(int minCapacity){
		if (minCapacity < 1) throw new IllegalArgumentException("initialCapacity must be a positive integer");
		
		this.minCapacity = minCapacity;
		container = (Entry[]) new LinearProbingHashTable.Entry[minCapacity];
		N = 0;
	}

	public LinearProbingHashTable(){
		this(DEFAULT_CAPACITY);
	}

	/*
	 * Implements HashTable<K, V>
	 */

	@Override
	public void put(K key, V value) {
		if (insertOrUpdate(key, value)) {
			N++;
			// Grow container if full
			if (N == container.length){
				resizeAndRehash(container.length*2);
			}
		}
	}

	@Override
	public V get(K key){
		int bucket = getBucket(key);
		if(container[bucket] != null){
			return container[bucket].value;
		}
		return null;
	}

	@Override
	public V remove(K key) {
		int bucket = getBucket(key);

		if (bucket == -1 || container[bucket] == null){
			return null;
		}

		V toReturn = container[bucket].value;
		container[bucket] = null;
		N--;

		//Remap entries that may now map to the freed slot
		int start = bucket;

		bucket = (bucket + 1) % container.length;
		while (container[bucket] != null){
			int newBucket = getBucket(container[bucket].key);

			if (newBucket != bucket){
				Entry entry = container[bucket];
				container[bucket] = null;
				insertOrUpdate(entry.key, entry.value);	
			}

			bucket = (bucket + 1) % container.length;

			if (bucket == start) break;
		}
		
		// Shrink container if too large
		if (N <= container.length/4 && container.length/2 > minCapacity){
			resizeAndRehash(container.length/2);
		}

		return toReturn;
	}	

	@Override
	public boolean contains(K key) {
		int index = getBucket(key);
		return index != -1 && container[index] != null;
	}

	@Override
	public int size() {
		return this.N;
	}

	/*
	 * UTIL
	 */

	private boolean insertOrUpdate(K key, V value) {
		int bucket = getBucket(key);
		if(container[bucket] != null){
			container[bucket].value = value;
		} else {
			container[bucket] = new Entry(key, value);
			return true; //new entry
		}
		return false; //existing entry
	}	

	private int getBucket(K key){
		return getBucket(key, container);
	}
	
	private int getBucket(K key, Entry[] theContainer){
		int bucket = hash(key) % theContainer.length;

		int start = bucket;
		while(theContainer[bucket] != null && !theContainer[bucket].key.equals(key)){
			bucket = (bucket+1) % theContainer.length;
			if(bucket == start){
				return -1;
			}
		}

		return bucket;
	}

	private static int hash(Object obj){
		int hashCode = obj.hashCode(); // assuming user has implemented hashcode
		return hashCode & 0x7fffffff; // mask to remove negative sign
	}
	
	
	private void resizeAndRehash(int newSize) {
		Entry[] newContainer = (Entry[]) new LinearProbingHashTable.Entry[newSize];
		
		//Rehashing entries
		for(int i=0; i < container.length; i++){
			Entry oldEntry = container[i];
			if(oldEntry != null){
				int newBucket = getBucket(oldEntry.key, newContainer);
				newContainer[newBucket] = oldEntry;
				container[i] = null; //avoid trashing				
			}
		}
		
		this.container = newContainer;	
	}

	protected int getCapacity(){
		return container.length;
	}

	public static void main(String[] args) {
		HashTable<Integer, String> map = new LinearProbingHashTable<Integer, String>();

		map.put(0, "paulo");
		map.put(10, "liana");
		map.put(Integer.MAX_VALUE, "ricardo");
		map.put(Integer.MIN_VALUE, "motta");
		map.put(-1, "gomes");
		map.put(1, "kilpp");

		System.out.println(map.get(0));
		System.out.println(map.get(10));
		System.out.println(map.get(Integer.MAX_VALUE));
		System.out.println(map.get(Integer.MIN_VALUE));
		System.out.println(map.get(-1));
		System.out.println(map.get(1));
	}

}