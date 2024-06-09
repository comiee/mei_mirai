package com.comiee.mei.robot;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.function.Function;

@SuppressWarnings("unused")
public class Config extends Properties {
    public Config() throws IOException {
        this("config.properties");
    }

    public Config(String path) throws IOException {
        load(getClass().getClassLoader().getResourceAsStream(path));
    }

    // 泛型方法来获取配置值
    public <T> T getValue(String key, Function<String, T> parser, T defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            if (defaultValue == null) {
                throw new NoSuchElementException("Key not found: " + key);
            } else {
                return defaultValue;
            }
        }
        return parser.apply(value);
    }

    // 无默认值时抛出异常
    public <T> T getValue(String key, Function<String, T> parser) {
        return getValue(key, parser, null);
    }

    public boolean getBool(String key) {
        return getValue(key, Boolean::parseBoolean);
    }

    public boolean getBool(String key, boolean defaultValue) {
        return getValue(key, Boolean::parseBoolean, defaultValue);
    }

    public int getInt(String key) {
        return getValue(key, Integer::parseInt);
    }

    public int getInt(String key, int defaultValue) {
        return getValue(key, Integer::parseInt, defaultValue);
    }

    public long getLong(String key) {
        return getValue(key, Long::parseLong);
    }

    public long getLong(String key, long defaultValue) {
        return getValue(key, Long::parseLong, defaultValue);
    }
}