package com.comiee.mei.testcase;


import com.comiee.mei.communication.Client;
import com.comiee.mei.message.DebugMsg;
import com.comiee.mei.demo.DemoClient;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    @Test
    public void testMultiMsg() throws Exception {
        Client client = new DemoClient();
        client.send(new DebugMsg().build("test"));
        Thread[] ts = new Thread[30];
        for (int i = 0; i < ts.length; i++) {
            int fi = i;
            ts[i] = new Thread(() -> {
                JsonElement ret = client.send(new DebugMsg().build("" + fi));
                assertEquals("" + fi, ret.getAsString());
            });
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
    }
}
