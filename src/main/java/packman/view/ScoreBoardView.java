package packman.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import packman.controller.ScoreBoardController;
import packman.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreBoardView {

    public VBox vbox;
    public VBox rankVBox;
    public VBox usernameVbox;
    public VBox highScoreVBox;
    public Button backButton;

    public void initialize() {
        ArrayList<User> allUser = ScoreBoardController.getInstance().sortedUsers();
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
//        allUser.add(User.onlineUser);
        int j = 0;
        for (int k = 0; k < allUser.size(); k++) {
            User user = allUser.get(k);
            k++;
            if (k != 1 && user.getHighScore() == allUser.get(k - 2).getHighScore()) {
                j++;
                addToVbox(k - j, user.getUsername(), user.getHighScore());
            } else {
                addToVbox(k, user.getUsername(), user.getHighScore());
                j = 0;
            }
            k--;
        }
    }

    public void addToVbox(int rank, String username, int highScore) {

        Region region = new Region();
        region.prefWidth(15);
        region.prefHeight(33);

        Text rankTXT = new Text();
        rankTXT.setText(String.valueOf(rank));
        rankTXT.setTextAlignment(TextAlignment.CENTER);

        Text usernameTXT = new Text();
        usernameTXT.setText(username);
        usernameTXT.setTextAlignment(TextAlignment.CENTER);

        Text scoreTXT = new Text();
        scoreTXT.setText(String.valueOf(highScore));
        scoreTXT.setTextAlignment(TextAlignment.CENTER);

        rankVBox.setAlignment(Pos.TOP_CENTER);
        rankVBox.getChildren().add(rankTXT);
        usernameVbox.setAlignment(Pos.TOP_CENTER);
        usernameVbox.getChildren().add(usernameTXT);
        highScoreVBox.setAlignment(Pos.TOP_CENTER);
        highScoreVBox.getChildren().add(scoreTXT);

//        HBox hBox = new HBox(region, rankTXT, usernameTXT, scoreTXT);
//        hBox.setAlignment(Pos.CENTER_LEFT);
//        hBox.prefHeight(33);
//        hBox.prefWidth(260);
//        vbox.getChildren().add(hBox);
    }

    public void back(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Main Menu");
            Main.stage.setScene(new Scene(root, 300, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
