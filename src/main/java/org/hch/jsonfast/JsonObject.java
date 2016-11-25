package org.hch.jsonfast;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hernan on 24/11/16.
 */
public class JsonObject implements JsonObjectMaker{

    private static final char[] NULL = {'n','u','l','l'};
    private static final char TS = '"';
    private static final char FS = ',';
    private LinkedHashMap<String, Object> map;

    public JsonObject() {
        map = new LinkedHashMap();
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

        for (Map.Entry<String, Object> entry : map.entrySet()) {
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
