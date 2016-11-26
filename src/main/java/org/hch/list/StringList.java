package org.hch.list;

import java.util.Arrays;

/**
 * Created by hernan on 26/11/16.
 */
public class StringList {
    private static final int DEFAULT_CAPACITY = 100;
    private char[][] data;
    private int off = 0;

    public StringList() {
        data = new char[DEFAULT_CAPACITY][];
    }

    public void add(String value) {
        add(value.toCharArray());
    }

    public void add(char[] value) {
        // if there is not enough room to store the value a new array is created.
        // (like in java.lang.ArrayList)
        if (off >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[off++] = value;
    }

    public void clear() {
        off = 0;
    }

    public int size() {
        return off;
    }

    public void add(int index, String value) {
        data[index] = value.toCharArray();
    }

    public String get(int index) {
        return data[index].toString();
    }

    public char[][] getData(){
        return Arrays.copyOfRange(data,0,off);
    }
}
