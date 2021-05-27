package packman.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import packman.controller.DatabaseController;
import packman.model.User;

import java.io.IOException;
import java.util.Optional;

public class SettingView {
    public Text usernameText;
    public Text highScoreText;
    public Text numOfLife;
    public ChoiceBox levelChoice;

    public void initialize() {
        usernameText.setText(usernameText.getText() + User.onlineUser.getUsername());
        highScoreText.setText(highScoreText.getText() + User.onlineUser.getHighScore());
        levelChoice.setItems(FXCollections.observableArrayList("Easy", "Hard"));
        levelChoice.setValue(User.onlineUser.getLevelOfGame());
        levelChoice.setTooltip(new Tooltip("Select Level"));
        numOfLife.setText(String.valueOf(User.onlineUser.getNumberOfLife()));
    }

    public void minusLife(ActionEvent actionEvent) {
        int life = User.onlineUser.getNumberOfLife();
        if (User.onlineUser.getNumberOfLife() != 3) {
            User.onlineUser.setNumberOfLife(life - 1);
            numOfLife.setText(String.valueOf(User.onlineUser.getNumberOfLife()));
        }
    }

    public void plusLife(ActionEvent actionEvent) {
        int life = User.onlineUser.getNumberOfLife();
        if (User.onlineUser.getNumberOfLife() != 5) {
            User.onlineUser.setNumberOfLife(life + 1);
            numOfLife.setText(String.valueOf(User.onlineUser.getNumberOfLife()));
        }
    }

    public void deleteAccount(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(String.format("Delete %s", User.onlineUser.getUsername()));
        alert.setHeaderText(String.format("Delete %s", User.onlineUser.getUsername()));
        alert.setContentText("Do you want to delete your account?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DatabaseController.deleteUser(User.onlineUser.getUsername());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Account deleted!");
            alert1.setTitle("Delete Account");
            alert1.setHeaderText("Delete Account");
            alert1.show();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
                Main.stage.setScene(new Scene(root, 300, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void back(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = null;
        try {
            User.onlineUser.setLevelOfGame((String) levelChoice.getValue());
            User.onlineUser.setNumberOfLife(Integer.parseInt(numOfLife.getText()));
            root = fxmlLoader.load();
            Main.stage.setTitle("Main Menu");
            Main.stage.setScene(new Scene(root, 300, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Change Password");
        dialog.setHeaderText("Change Password");
        dialog.setGraphic(new Circle(15, Color.RED));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Enter your new Password :"), pwd);
        dialog.getDialogPane().setContent(content);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            User.onlineUser.setPassword(result.get());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText(" Password changed!");
            alert1.setTitle("Change Password");
            alert1.setHeaderText("Change Password");
            alert1.show();
        }
    }

    public void manageMaps(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("map_manager.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Manage Maps");
            Main.stage.setScene(new Scene(root, 900, 770));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
