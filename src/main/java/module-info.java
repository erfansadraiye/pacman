module pacman {
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    opens packman.view to javafx.fxml;
    exports packman.view;
    opens packman.controller to com.google.gson;
    exports packman.controller;
    opens packman.model to com.google.gson;
    exports packman.model;
}