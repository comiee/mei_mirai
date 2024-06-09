package com.comiee.mei.testcase;

import com.comiee.mei.robot.Config;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

public class ConfigTest {
    private final Config config;

    ConfigTest() throws IOException {
        config = new Config("test.properties");
    }

    @Test
    void testInt() {
        assertEquals(1, config.getInt("a"));
    }

    @Test
    void testBool() {
        assertTrue(config.getBool("b"));
    }

    @Test
    void testString() {
        assertEquals("hi", config.getProperty("c"));
    }
}
