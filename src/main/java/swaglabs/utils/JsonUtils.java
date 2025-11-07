package swaglabs.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    public static JsonObject getJsonData(String fileName) {
        String basePath = ConfigReader.getProperty("testDataPath");
        String filePath = basePath + fileName;

        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to read JSON file: " + filePath);
        }
    }
}
