package org.hch.jsonfast;

import org.hch.list.StringList;

/**
 * @author hernan on 24/11/16.
 */
public class JsonArray{

    private static final char[] NULL = {'n','u','l','l'};
    private static final char TS = '"';
    private static final char FS = ',';
    private int off = 0;
    private StringList array;

    public JsonArray(){
        array = new StringList();
    }

    public JsonArray put(String value){
        if (value == null)
            return putNull();

        array.add(new StringBuilder().append(TS).append(value).append(TS).toString());
        return this;
    }

    public JsonArray put(Object value){
        array.add(value.toString());
        return this;
    }

    public JsonArray put(int index, Object value){
        array.add(index, value.toString());
        return this;
    }

    public JsonArray putNull(){
        array.add(NULL);
        return this;
    }

    public String get(int index){
        return array.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(255);
        stringBuilder.append('[');
        for (char[] value: array.getData()) {
            stringBuilder
                    .append(value)
                    .append(FS);
        }
        //Remove the last ","
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
