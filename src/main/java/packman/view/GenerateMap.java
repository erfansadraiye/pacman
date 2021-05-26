package packman.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import packman.controller.DatabaseController;
import packman.model.Map;
import packman.model.User;

import java.io.IOException;

public class GenerateMap {

    public GridPane gridPane;
    Map map;
    public void initialize(){
        map = new Map();
        map.passGridPaneChildren(gridPane);
    }

    public void no(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("map_manager.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Manage Maps");
            Main.stage.setScene(new Scene(root, 930, 1000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void yes(ActionEvent actionEvent) {
        User.onlineUser.getMaps().add(map);
        DatabaseController.updateUser(User.onlineUser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Map added");
        alert.setHeaderText("Map added");
        alert.setContentText("Map added to your maps");
        alert.show();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("map_manager.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Manage Maps");
            Main.stage.setScene(new Scene(root, 930, 1000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate(ActionEvent actionEvent) {
        map = new Map();
        map.passGridPaneChildren(gridPane);
    }
}
