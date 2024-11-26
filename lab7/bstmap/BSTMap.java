package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size = 0;
    private BSTNode root;

    /** Removes all mappings from this map. */
    public void clear() {
        size = 0;
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return false;
        }
        return root.get(key, root) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }
        BSTNode lookup = root.get(key, root);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (root != null) {
            root.put(key, value, root);
        } else {
            root = new BSTNode(key, value, null, null);
            size += 1;
        }

    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (key.compareTo(root.key) == 0) {
            BSTNode tmp = root;
            root = root.remove(key, root);
            size -= 1;
            return tmp.val;
        }
        BSTNode lookup = root.get(key, root);
        if (lookup == null) {
            return null;
        }
        BSTNode tmp = lookup;
        lookup.remove(key, root);
        size -= 1;
        if (tmp == null) {
            return null;
        }
        return tmp.val;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (!get(key).equals(value)) {
            return remove(key);
        }
        return null;
    }


    private class BSTNode {
        K key;
        V val;
        BSTNode left, right;

        private BSTNode(K k, V v, BSTNode l, BSTNode r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }

        private BSTNode get(K key, BSTNode n) {
            if (n == null) {
                return null;
            }
            if (key.compareTo(n.key) < 0) {
                n = get(key, n.left);
            } else if (key.compareTo(n.key) > 0) {
                n = get(key, n.right);
            }
            return n;
        }

        private BSTNode put(K key, V value, BSTNode n) {
            if (n == null) {
                n = new BSTNode(key, value, null, null);
                size += 1;
            }
            if (key.compareTo(n.key) < 0) {
                n.left = put(key, value, n.left);
            } else if (key.compareTo(n.key) > 0) {
                n.right = put(key, value, n.right);
            } else {
                n.val = value;
            }
            return n;
        }

        private BSTNode findMaxNodeLeftBST(BSTNode n) {
            if (n == null) {
                return null;
            }
            if (n.right == null) {
                BSTNode tmp = n;
                n.remove(n.key, n);
                return tmp;
            }
            return findMaxNodeLeftBST(n.right);
        }


        private BSTNode remove(K key, BSTNode n) {
            if (n == null) {
                return null;
            }
            if (key.compareTo(n.key) < 0) {
                n.left = remove(key, n.left);
                return n;
            } else if (key.compareTo(n.key) > 0) {
                n.right = remove(key, n.right);
                return n;
            }
            else {
                if (n.left == null && n.right == null) {
                    return null;
                } else if (n.left != null && n.right == null) {
                    return n.left;
                } else if (n.left == null && n.right != null) {
                    return n.right;
                } else {
                    BSTNode tmp = n;
                    BSTNode maxLeftNode = findMaxNodeLeftBST(n.left);
                    n.key = maxLeftNode.key;
                    n.val = maxLeftNode.val;
                    return tmp;
                }
            }
        }

    }



    public void printInOrder() {
        for(K key : this) {
            System.out.println(key);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private BSTNode curr;

        private BSTIterator() {
            curr = root;
        }
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        private K next(BSTNode n) {
            K tmp = curr.key;
            next(curr.left);
            next(curr.right);
            return tmp;
        }


        @Override
        public K next() {
            return next(curr);
        }
    }



    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }


}