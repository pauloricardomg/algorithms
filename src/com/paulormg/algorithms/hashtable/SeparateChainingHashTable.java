package com.paulormg.algorithms.hashtable;

public class SeparateChainingHashTable<K,V> implements HashTable<K,V> {

	public final class Entry {
		K key;
		V value;
		Entry next;

		protected Entry(K key, V value, Entry next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return String.format("(%s -> %s)", key.toString(), value.toString());
		}
	}	

	protected static final int INITIAL_CAPACITY = 5;

	private Entry[] container;
	private int N;
	private int minCapacity;

	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable(int minCapacity){
		this.container = (Entry[]) new SeparateChainingHashTable.Entry[minCapacity];
		this.N = 0;
		this.minCapacity = minCapacity;
	}

	public SeparateChainingHashTable(){
		this(INITIAL_CAPACITY);
	}

	/*
	 * Implements HashTable<K, V>
	 */


	@Override
	public void put(K key, V value){

		Entry entry = getEntry(key);
		if (entry == null){
			insert(key, value);
		} else {
			entry.value = value;
		}
	}

	@Override
	public V get(K key){

		Entry entry = getEntry(key);
		if (entry != null){
			return entry.value;
		}

		return null;
	}

	@Override
	public V remove(K key) {

		int bucket = getBucket(key);	
		V toReturn = null;

		Entry prev = null;
		Entry current = container[bucket];
		while(current != null){
			if (current.key.equals(key)){
				//Remove entry
				if(prev == null){
					container[bucket] = current.next;
				} else {
					prev.next = current.next;	
				}
				toReturn = current.value;
				N--;
				break;
			}

			prev = current;
			current = current.next;
		}


		// Shrink container if too large
		if (N <= container.length/4 && container.length/2 > minCapacity){
			resizeAndRehash(container.length/2);
		}

		return toReturn;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public boolean contains(K key) {
		return getEntry(key) != null;
	}

	/*
	 * UTIL
	 */

	private int getBucket(K key) {
		return hash(key) % container.length;
	}	

	private Entry getEntry(K key) {
		int bucket = getBucket(key);

		Entry entry = container[bucket];
		while(entry != null && !entry.key.equals(key)){
			entry = entry.next;
		}

		return entry;
	}

	private static int hash(Object obj){
		int hashCode = obj.hashCode(); // assuming user has implemented hashcode
		return hashCode & 0x7fffffff; // mask to remove negative sign
	}

	private void insert(K key, V value) {

		int bucket = hash(key) % container.length;
		container[bucket] = new Entry(key, value, container[bucket]);	
		N++;

		// Grow container if full
		if (N == container.length){
			resizeAndRehash(container.length*2);
		}
	}

	private void resizeAndRehash(int newSize){

		Entry[] newContainer = (Entry[]) new SeparateChainingHashTable.Entry[newSize];

		//Rehashing entries
		for(int i=0; i < container.length; i++){
			Entry oldEntry = container[i];
			while(oldEntry != null){
				int newBucket = hash(oldEntry.key) % newSize;
				newContainer[newBucket] = new Entry(oldEntry.key, oldEntry.value, newContainer[newBucket]);
				oldEntry = oldEntry.next;
			}
			container[i] = null; //avoid trashing
		}

		this.container = newContainer;
	}

	protected int getCapacity(){
		return container.length;
	}

}