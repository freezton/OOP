package com.example.demo.serializers;

public class SerializerInfo {
    private Class<? extends Serializer> serializer;
    private final String name;
    private final String extension;

    public SerializerInfo(Class<? extends Serializer> serializer, String name, String extension) {
        this.serializer = serializer;
        this.name = name;
        this.extension = extension;
    }

    public Serializer getSerializer() throws InstantiationException, IllegalAccessException {
        return this.serializer.newInstance();
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return name;
    }
}
