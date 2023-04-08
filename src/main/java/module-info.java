module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.temp to javafx.graphics;
    exports com.example.demo.controllers;
    exports com.example.demo.classes;

    opens com.example.demo.classes to javafx.base;
    opens com.example.demo.controllers to javafx.fxml;
    exports com.example.demo.enums;
    opens com.example.demo.enums to javafx.base;
}