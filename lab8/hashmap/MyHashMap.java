package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size = 0;
    private int numOfBuckets = 16; //number of buckets
    private double loadFactor = 0.75;
    private Set<K> keys = new HashSet<>();

    private void setBuckets() {
        for (int i = 0; i < numOfBuckets; i++) {
            buckets[i] = createBucket();
        }
    }

    /** Constructors */
    public MyHashMap() {
        buckets = new Collection[numOfBuckets];
        setBuckets();
    }

    public MyHashMap(int initialSize) {
        numOfBuckets = initialSize;
        buckets = new Collection[numOfBuckets];
        setBuckets();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        numOfBuckets = initialSize;
        loadFactor = maxLoad;
        buckets = new Collection[numOfBuckets];
        setBuckets();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    private void resize() {
        MyHashMap tmp = new MyHashMap(numOfBuckets * 2, loadFactor);
        for (K k : keys) {
            tmp.put(k, get(k));
        }
        numOfBuckets *= 2;
        buckets = tmp.buckets;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return Math.floorMod(h, numOfBuckets);
    }

    public void clear() {
        size = 0;
        setBuckets();
    }

    public boolean containsKey(K key) {
        for (Node n : buckets[hash(key)]) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        V val = null;
        for (Node n : buckets[hash(key)]) {
            if (n.key.equals(key)) {
                val = n.value;
                break;
            }
        }
        return val;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (!containsKey(key)) {
           if (((double) (size + 1) / (double) numOfBuckets) > loadFactor) {
                resize();
           }
           buckets[hash(key)].add(createNode(key, value));
           size += 1;
           keys.add(key);
       }
       else {
           for (Node n : buckets[hash(key)]) {
               if (n.key.equals(key)) {
                   n.value = value;
               }
           }
       }
    }

    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator<>();
    }

    private class hashMapIterator<K> implements Iterator<K> {
        Object[] keyArray;
        int index;

        private hashMapIterator() {
            keyArray = keys.toArray();
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public K next() {
            index += 1;
            return (K) keyArray[index - 1];
        }
    }

    public Set<K> keySet() {
        return keys;
    }

    public V remove(K key) {
        if (containsKey(key)) {
            for (Node n : buckets[hash(key)]) {
                if (n.key.equals(key)) {
                    V tmp = n.value;
                    buckets[hash(key)].remove(n);
                    return tmp;
                }
            }
        }
       return null;
    }

    public V remove(K key, V value) {
        if (containsKey(key)) {
            for (Node n : buckets[hash(key)]) {
                if (n.key.equals(key) && n.value == value) {
                    V tmp = n.value;
                    buckets[hash(key)].remove(n);
                    return tmp;
                }
            }
        }
        return null;
    }

}
