package com.example.demo.utils;

import com.example.demo.serializers.SerializerInfo;
import javafx.stage.FileChooser;

import java.util.Map;

abstract public class FileChooserManager {
    public static void setFilters(FileChooser fileChooser, Map<String, SerializerInfo> serializers) {
        for (String extension : serializers.keySet()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(serializers.get(extension).toString(), "*." + extension));
        }
    }

    public static void setFiltersForEncoder(FileChooser fileChooser, Map<String, SerializerInfo> serializers) {
        for (String extension : serializers.keySet()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(serializers.get(extension).toString(), "*." + extension, "*.base32", "*.base64"));
        }
    }
}
