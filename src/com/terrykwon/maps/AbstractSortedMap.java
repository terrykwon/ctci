package com.terrykwon.maps;

import com.terrykwon.Entry;
import java.util.Comparator;

public abstract class AbstractSortedMap<K, V> extends AbstractMap<K, V>
        implements SortedMap<K, V> {

    /**
     * The comparator defining the ordering of keys in the map.
     */
    private Comparator<K> comparator;

    protected AbstractSortedMap() {
        this.comparator = null;
    }

    protected AbstractSortedMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    // Utilities to safeCompare entries and keys

    protected int compare(Entry<K, V> e1, Entry<K, V> e2) {
        return compare(e1.getKey(), e2.getKey());
    }

    protected int compare(K key, Entry<K, V> entry) {
        return compare(key, entry.getKey());
    }

    protected int compare(Entry<K, V> entry, K key) {
        return compare(entry.getKey(), key);
    }

    protected int compare(K key1, K key2) {
        return compare(key1, key2);
    }

    @SuppressWarnings("unchecked")
    private int safeCompare(Object o1, Object o2) {
        return (comparator == null)
                ? ((Comparable<? super K>) o1).compareTo(((K) o2)) // Lower bounded wildcard
                : comparator.compare((K) o1,(K) o2);
    }

    /**
     * Checks whether a key is comparable.
     *
     * A key should either extend {@link java.lang.Comparable} or be compared through a custom {@link Comparator}
     * passed through the constructor.
     */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return compare(key, key) == 0; // Compare key to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

}
