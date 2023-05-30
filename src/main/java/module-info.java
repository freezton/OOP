module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
//    requires org.apache.commons.codec;
    requires BaseEncoder;
    requires Base32encoder;
    requires Base64encoder;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.serializers to com.fasterxml.jackson.databind;
//    exports com.example.demo.classes to com.fasterxml.jackson.databind;
    exports com.example.demo.temp to javafx.graphics;
    exports com.example.demo.controllers;
    exports com.example.demo.classes;

    opens com.example.demo.classes to javafx.base, com.fasterxml.jackson.databind;
    opens com.example.demo.controllers to javafx.fxml;
    exports com.example.demo.enums;
    opens com.example.demo.enums to javafx.base;
}