package com.example.demo;

import com.example.demo.classes.Book;
import com.example.demo.classes.Product;
import com.example.demo.enums.Genre;
import com.example.demo.serializers.Serializer;
import com.example.demo.serializers.TextSerializer;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SerializationTest {

    final String TEXT_PATH = "src\\test\\java\\com\\example\\demo\\out.txt";
    final String BIN_PATH = "src\\test\\java\\com\\example\\demo\\out.bin";
    final String JSON_PATH = "src\\test\\java\\com\\example\\demo\\out.json";
    @Test
    public void serializationTest() {
        Serializer serializer = new TextSerializer();
        List<Product> products = Arrays.asList(
                new Book(1, "1984", 16.99, "desc", "author", "publisher", Genre.DYSTOPIA)
        );
        serializer.serialize(products, null, TEXT_PATH);
        try {
            String fileContent = Files.readString(Paths.get(TEXT_PATH));
            System.out.println(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
