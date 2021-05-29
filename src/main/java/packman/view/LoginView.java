package packman.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import packman.controller.LoginController;

import java.io.IOException;

public class LoginView {

    @FXML
    public TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void signup(ActionEvent mouseEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {
            LoginController.getInstance().createUser(username, password);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("User created");
            usernameField.clear();
            passwordField.clear();
            alert.show();
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            usernameField.clear();
            passwordField.clear();
            alert.show();
        }

    }

    public void login(ActionEvent mouseEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {
            LoginController.getInstance().loginUser(username, password);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
                Main.stage.setTitle("Main Menu");
                Main.stage.setScene(new Scene(root, 300, 450));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            usernameField.clear();
            passwordField.clear();
            alert.show();
        }
    }
}

