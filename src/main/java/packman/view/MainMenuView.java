package packman.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import packman.controller.DatabaseController;
import packman.model.User;

import java.io.IOException;

public class MainMenuView {

    public Button newGameButton;
    public Text usernameText;
    public Text highScoreText;



    public void initialize() {
        usernameText.setText(usernameText.getText() + User.onlineUser.getUsername());
        highScoreText.setText(highScoreText.getText() + User.onlineUser.getHighScore());

    }

    public void newGame(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Pac Man");
            Main.stage.setScene(new Scene(root,1000,770));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scoreboard(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scoreboard.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Score Board");
            Main.stage.setScene(new Scene(root, 300, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setting(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setting.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Setting");
            Main.stage.setScene(new Scene(root, 300, 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            DatabaseController.updateUser(User.onlineUser);
            User.onlineUser = null;
            Main.stage.setTitle("Login");
            Main.stage.setScene(new Scene(root, 300, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
