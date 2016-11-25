package org.hch.jsonfast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hernan on 24/11/16.
 */
public class JsonArray implements JsonArrayMaker{

    private static final String NULL = "null";
    private static final char TS = '"';
    private static final char FS = ',';
    private List<String> array;

    public JsonArray(){
        array = new ArrayList<>();
    }

    public JsonArray put(String value){
        if (value == null)
            return putNull();

        array.add(TS+value+TS);
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
        for (String value: array) {
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
