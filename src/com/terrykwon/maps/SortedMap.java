package com.terrykwon.maps;

import com.terrykwon.Entry;

public interface SortedMap<K, V> extends Map<K, V> {

    /**
     * Returns the entry with the smallest key.
     */
    Entry<K, V> firstEntry();

    /**
     * Returns the entry with the largest key.
     */
    Entry<K, V> lastEntry();

    /**
     * Returns the entry with the smallest key greater or equal to {@param key}.
     */
    Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with the largest key smaller or equal to {@param key}
     */
    Entry<K, V> floorEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with the smallest key strictly greater than {@param key}.
     */
    Entry<K, V> higherEntry(K key) throws IllegalArgumentException;

    /**
     * Returns the entry with the largest key strictly leses than {@param key}.
     */
    Entry<K, V> lowerEntry(K key) throws IllegalArgumentException;

    /**
     * Returns an {@link java.lang.Iterable} of entries beginning with {@param key1},
     * and ending with (and not including) {@param key2}.
     */
    Iterable<Entry<K, V>> subMap(K key1, K key2) throws IllegalArgumentException;
}
