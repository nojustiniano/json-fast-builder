package org.hch.jsonfast;

/**
 * Created by hernan on 24/11/16.
 */
public class JsonObjectBuilder implements JsonObjectMaker{

    private static final char[] NULL = {'n','u','l','l'};
    private static final char TS = '"';
    private static final char FS = ',';
    private StringBuilder stringBuilder;


    public JsonObjectBuilder(){
        stringBuilder = new StringBuilder(255).append('{');
    }

    public JsonObjectBuilder put(String key, String value){
        if(value == null)
            return putNull(key);

        stringBuilder
                .append(TS)
                .append(key)
                .append(new char[]{TS, ':', TS})
                .append(value)
                .append(new char[]{TS,FS});
        return this;
    }

    public JsonObjectBuilder put(String key, Object value){
        stringBuilder
                .append(TS)
                .append(key)
                .append(new char[]{TS, ':'})
                .append(value)
                .append(FS);
        return this;
    }

    public JsonObjectBuilder putNull(String key){
        stringBuilder
                .append(TS)
                .append(key)
                .append(new char[]{TS, ':'})
                .append(JsonObjectBuilder.NULL)
                .append(FS);
        return this;
    }

    @Override
    public String toString() {
        stringBuilder.setLength(stringBuilder.length()-1);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
