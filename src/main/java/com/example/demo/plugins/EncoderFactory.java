package com.example.demo.plugins;

import com.example.demo.serializers.SerializerInfo;
import javafx.stage.FileChooser;

import java.util.Map;

public class EncoderFactory {
    public void setFilters(FileChooser fileChooser, Map<String, SerializerInfo> serializers) {
        for (String extension : serializers.keySet()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(serializers.get(extension).toString(), "*." + extension));
        }
    }

    public SerializerInfo getSerializerInfo(String extension, Map<String, SerializerInfo> serializers) {
        return serializers.get(extension);
    }
}
