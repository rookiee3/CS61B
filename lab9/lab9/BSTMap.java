package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        //throw new UnsupportedOperationException();
        if (key == null) {
            throw new java.lang.IllegalArgumentException("key can not be null");
        } else if (p == null) {
            return null;
        }
        if (p.left == null && p.right == null && key.compareTo(p.key) != 0) {
            return null;
        } else {
            if (key.compareTo(p.key) == 0) {
                return p.value;
            } else if (key.compareTo(p.key) < 0) {
                return getHelper(key, p.left);
            } else {
                return getHelper(key, p.right);
            }
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        //throw new UnsupportedOperationException();
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        //throw new UnsupportedOperationException();
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        }
        p.value = value;
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        //throw new UnsupportedOperationException();
        if (key == null || value == null) {
            throw new java.lang.IllegalArgumentException("key or value can't be null");
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        //throw new UnsupportedOperationException();
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void traverseTree(Node p, Set<K> quene) {
        if (p == null) {
            return;
        }
        quene.add(p.key);
        traverseTree(p.left, quene);
        traverseTree(p.right, quene);
    }
    @Override
    public Set<K> keySet() {
        //throw new UnsupportedOperationException();
        Set<K> set = new HashSet<>();
        traverseTree(root, set);
        return set;
    }

    private Node remove(Node p, K key) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return remove(p.left, key);
        } else if (cmp > 0) {
            return remove(p.right, key);
        }
        if (p.left == null) {
            return p.right;
        } else if (p.right == null) {
            return p.left;
        }
        //with two sub trees
        Node t = p;
        p = minimum(t.right);
        p.right = removeMinimum(t.right);
        p.left = t.left;
        size--;
        return p;
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        //throw new UnsupportedOperationException();
        V retValue = get(key);
        if (key == null) {
            return null;
        }
        root = remove(root, key);
        return retValue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private Node minimum(Node p) {
        if (p.left == null) {
            return p;
        }
        return minimum(p.left);
    }
    private Node removeMinimum(Node p) {
        if (p.left == null) {
            return p.right;
        }
        p.left = removeMinimum(p.left);
        size--;
        return p;
    }

    @Override
    public Iterator<K> iterator() {
        //throw new UnsupportedOperationException();
        return keySet().iterator();
    }
}
