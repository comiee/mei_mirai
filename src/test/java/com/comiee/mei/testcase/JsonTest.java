package com.comiee.mei.testcase;

import com.comiee.mei.communal.json.JsonTool;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class JsonTest {
    private final JsonObject jsonObject = JsonTool.createObject(
            "cmd", "test",
            "value", JsonTool.createObject(
                    "array", new int[]{1, 2, 3},
                    "list", List.of(
                            JsonTool.createObject(
                                    "jsonArray", JsonTool.createArray(4, 5, 6),
                                    "int", 7,
                                    "float", 8.9
                            ),
                            JsonTool.createObject(
                                    "boolean", true,
                                    "null", null,
                                    "string", "{,}"
                            )
                    )
            )
    );
    private final String string =
            "{\"cmd\":\"test\",\"value\":{" +
                    "\"array\":[1,2,3]," +
                    "\"list\":[{" +
                    "\"jsonArray\":[4,5,6]," +
                    "\"int\":7," +
                    "\"float\":8.9" +
                    "},{" +
                    "\"boolean\":true," +
                    "\"null\":null," +
                    "\"string\":\"{,}\"}" +
                    "]}}";

    @Test
    void testJsonDump() {
        assertEquals(string, JsonTool.dump(jsonObject));
    }

    @Test
    void testJsonParse() {
        assertEquals(jsonObject, JsonTool.parse(string));
    }
}
