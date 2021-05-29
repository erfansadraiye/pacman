package packman.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        fxmlLoader.setController(GameView.getInstance());
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Pacman");
            Main.stage.setX(300);
            Main.stage.setY(5);
            Scene scene = new Scene(root, 1000, 770);
            scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W)
                    GameView.getInstance().pacman.setDirectionUp();
                else if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT)
                    GameView.getInstance().pacman.setDirectionRight();
                else if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN)
                    GameView.getInstance().pacman.setDirectionDown();
                else if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A)
                    GameView.getInstance().pacman.setDirectionLeft();
                GameView.getInstance().ghostTimeLine.play();
                GameView.getInstance().pacmanTimeLine.play();
            });
//            GameView.getInstance().scene = scene;
            Main.stage.setScene(scene);
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
