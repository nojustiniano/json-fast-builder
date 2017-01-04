package org.hch.list;

import java.util.*;

/**
 * Created by hernan on 30/11/16.
 */
public class CustomMapArray<K, V> implements Map<K, V> {
    private Entry<K, V>[] entrys = new Entry[DEFAULT_SIZE];
    private int off = 0;
    private static final int DEFAULT_SIZE = 10;

    @Override
    public int size() {
        return off;
    }

    @Override
    public boolean isEmpty() {
        return off == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(Object key, Object value) {
        if (off >= entrys.length) {
            entrys = Arrays.copyOf(entrys, entrys.length + DEFAULT_SIZE);
        }
        entrys[off] = new AbstractMap.SimpleEntry(key, value);
        return entrys[off++].getValue();
    }

    public Entry[] getArray(){
        Entry[] result = Arrays.copyOf(entrys, off);
        return result;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new LinkedHashSet<>(Arrays.asList(entrys));
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
