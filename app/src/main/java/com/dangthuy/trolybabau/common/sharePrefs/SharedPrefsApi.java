package com.dangthuy.trolybabau.common.sharePrefs;

import java.util.Set;

/**
 * Interface for SharePrefs
 */
public interface SharedPrefsApi {

    <T> T get(String key, Class<T> clazz);

    <T> void put(String key, T data);

    void remove(String key);

    void removeAll();

    Set getAllKey();
}
