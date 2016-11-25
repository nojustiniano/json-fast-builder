package org.hch.jsonfast;

/**
 * Created by hernan on 25/11/16.
 */
public interface JsonArrayMaker {
    JsonArrayMaker put(String value);
    JsonArrayMaker put(Object value);
    JsonArrayMaker putNull();
}
