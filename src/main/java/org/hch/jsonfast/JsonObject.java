package org.hch.jsonfast;

import org.hch.list.CustomMapArray;

import java.util.Map;

/**
 * @author hernan on 24/11/16.
 */
public class JsonObject{

    private static final String NULL = "null";
    private static final char TS = '"';
    private static final char FS = ',';
    private CustomMapArray map;

    public JsonObject() {
        map = new CustomMapArray();
    }

    public JsonObject put(String key, String value){
        if (value == null)
            return putNull(key);

        map.put(key, TS+value+TS);
        return this;
    }

    public JsonObject put(String key, Object value){
        map.put(key, value);
        return this;
    }

    public JsonObject putNull(String key){
        map.put(key, NULL);
        return this;
    }

    public Object get(String key){
        return map.get(key);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(255);
        stringBuilder.append('{');

        for (Map.Entry<String, Object> entry : map.getArray()) {
            stringBuilder
                    .append(TS)
                    .append(entry.getKey())
                    .append(new char[] {TS,':'})
                    .append(entry.getValue())
                    .append(FS);
        }

        //Remove the last ","
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
