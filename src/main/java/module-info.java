module untitled {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    opens packman.view to javafx.fxml;
    exports packman.view;
    opens packman.controller to com.google.gson;
    exports packman.controller;
    opens packman.model to com.google.gson;
    exports packman.model;
}