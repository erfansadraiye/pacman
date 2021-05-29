package packman.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import packman.controller.DatabaseController;
import packman.model.Map;
import packman.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class MapManager {
    public GridPane gridPane;
    public Text selectedNumber;
    public Text totalNumber;
    ArrayList<Map> maps;
    User user;

    public void initialize() {
        user = User.onlineUser;
        maps = user.getMaps();
        maps.get(user.getIndexOfSelectedMap()).passGridPaneChildren(gridPane);
        totalNumber.setText(String.valueOf(user.numberOfMaps()));
        selectedNumber.setText(String.valueOf(user.getIndexOfSelectedMap() + 1));
    }

    public void back(ActionEvent actionEvent) {
        user.setIndexOfSelectedMap((user.getIndexOfSelectedMap() - 1 + maps.size()) % maps.size());
        selectedNumber.setText(String.valueOf(user.getIndexOfSelectedMap() + 1));
        maps.get(user.getIndexOfSelectedMap()).passGridPaneChildren(gridPane);
    }

    public void next(ActionEvent actionEvent) {
        user.setIndexOfSelectedMap((user.getIndexOfSelectedMap() + 1) % maps.size());
        selectedNumber.setText(String.valueOf(user.getIndexOfSelectedMap() + 1));
        maps.get(user.getIndexOfSelectedMap()).passGridPaneChildren(gridPane);
    }

    public void generateNewMap(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generate_map.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Generate new Map");
            Main.stage.setScene(new Scene(root, 900, 770));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectMap(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setting.fxml"));
        Parent root = null;
        try {
            user.setIndexOfSelectedMap(Integer.parseInt(selectedNumber.getText()) - 1);
            DatabaseController.updateUser(user);
            root = fxmlLoader.load();
            Main.stage.setTitle("Setting");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Map selected");
            alert.setHeaderText("Map selected");
            alert.setContentText("Map selected");
            alert.show();
            Main.stage.setScene(new Scene(root, 300, 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
