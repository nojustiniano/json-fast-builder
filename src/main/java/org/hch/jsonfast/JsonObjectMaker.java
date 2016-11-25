package org.hch.jsonfast;

/**
 * Created by hernan on 25/11/16.
 */
public interface JsonObjectMaker {
    JsonObjectMaker put(String key, String value);
    JsonObjectMaker put(String key, Object value);
    JsonObjectMaker putNull(String key);
}
