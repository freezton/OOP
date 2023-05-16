package com.example.demo.factories;

import com.example.demo.serializers.SerializerInfo;
import com.example.demo.serializers.BinarySerializer;
import com.example.demo.serializers.JsonSerializer;
import com.example.demo.serializers.TextSerializer;
import javafx.stage.FileChooser;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory {
    private Map<String, SerializerInfo> serializers = new HashMap<>();

    public SerializerFactory() {
        serializers.put("txt", new SerializerInfo(TextSerializer.class, "Text document", "txt"));
        serializers.put("bin", new SerializerInfo(BinarySerializer.class, "Binary file", "bin"));
        serializers.put("json", new SerializerInfo(JsonSerializer.class, "JSON file", "json"));
    }

    public void setFilters(FileChooser fileChooser) {
        for (String extension : serializers.keySet()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(serializers.get(extension).toString(), "*." + extension));
        }
    }

    public SerializerInfo getSerializerInfo(String extension) {
        return serializers.get(extension);
    }
}
