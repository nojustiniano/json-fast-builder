package org.hch.jsonfast;

/**
 * Created by hernan on 24/11/16.
 */
public class JsonArrayBuilder implements JsonArrayMaker{
    private static final char[] NULL = {'n','u','l','l'};
    private static final char TS = '"';
    private static final char FS = ',';
    private StringBuilder stringBuilder;


    public JsonArrayBuilder(){
        stringBuilder = new StringBuilder(255).append('[');
    }

    public JsonArrayBuilder put(String value){
        if(value == null)
            return putNull();

        stringBuilder
                .append(TS)
                .append(value)
                .append(new char[]{TS,FS});
        return this;
    }

    public JsonArrayBuilder put(Object value){
        stringBuilder
                .append(value)
                .append(FS);
        return this;
    }

    public JsonArrayBuilder putNull(){
        stringBuilder
                .append(NULL)
                .append(FS);
        return this;
    }

    @Override
    public String toString() {
        stringBuilder.setLength(stringBuilder.length()-1);
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
