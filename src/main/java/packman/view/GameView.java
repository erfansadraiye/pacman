package packman.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import packman.controller.DatabaseController;
import packman.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GameView {

    private static GameView gameView;

    public static GameView getInstance() {
        if (gameView == null)
            gameView = new GameView();
        return gameView;
    }

    private GameView() {

    }

    //    public Scene scene;
    public static GridPane savedGridPane;
    public Map map;
    public GridPane gridPane;
    public Button pauseButton;
    public Text lifeText;
    public Text scoreText;
    public int life;
    public int score;
    public int crossed;
    public Pacman pacman;
    ArrayList<Ghost> ghosts;
    public Ghost blueGhost;
    public Ghost redGhost;
    public Ghost pinkGhost;
    public Ghost orangeGhost;
    public Timeline pacmanTimeLine;
    public Timeline ghostTimeLine;


    public void initialize() {
        if (map == null) {
            map = User.onlineUser.getMaps().get(User.onlineUser.getIndexOfSelectedMap());
            score = 0;
            life = User.onlineUser.getNumberOfLife();
            map.passGridPaneChildren(gridPane);
            pacman = new Pacman(gridPane);
            start();
            //todo reset
        } else
            gridPane.getChildren().addAll(savedGridPane.getChildren());
//            map.passGridPaneChildren(gridPane);
        updateScoreAndLife();
        run();
    }

    public void run() {
        ghostTimeLine = new Timeline();
        ghostTimeLine.setCycleCount(Animation.INDEFINITE);
        KeyFrame ghostFrame = new KeyFrame(Duration.seconds(0.3), actionEvent -> {
            Cell cell = null;
            if (getAccidentGhost() != null) {
                lose();
                updateScoreAndLife();
                return;
            }
            label:
            for (Ghost ghost : ghosts) {
                Integer[] direction = generateRandomDirection();
                for (Integer integer : direction) {
                    switch (integer) {
                        case 1://Right
                            cell = (Cell) getNodeByRowColumnIndex(ghost.getI(), ghost.getJ() + 1, gridPane);
                            if (!cell.isBlocked()) {
                                ghost.moveRight();
                                swap(ghost, cell);
                                continue label;
                            } else
                                break;
                        case 2://Down
                            cell = (Cell) getNodeByRowColumnIndex(ghost.getI() + 1, ghost.getJ(), gridPane);
                            if (!cell.isBlocked()) {
                                swap(ghost, cell);
                                ghost.moveDown();
                                continue label;
                            } else
                                break;
                        case 3://Up
                            cell = (Cell) getNodeByRowColumnIndex(ghost.getI() - 1, ghost.getJ(), gridPane);
                            if (!cell.isBlocked()) {
                                swap(ghost, cell);
                                ghost.moveUp();
                                continue label;
                            } else
                                break;
                        case 4://Left
                            cell = (Cell) getNodeByRowColumnIndex(ghost.getI(), ghost.getJ() - 1, gridPane);
                            if (!cell.isBlocked()) {
                                swap(ghost, cell);
                                ghost.moveLeft();
                            } else
                                break;
                    }
                }
            }
        });
        KeyFrame pacmanFrame = new KeyFrame(Duration.seconds(0.2), actionEvent -> {
//            getInput();
            Cell cell = null;
            if (getAccidentGhost() != null) {
                lose();
                updateScoreAndLife();
                return;
            }
            switch (pacman.getDirection()) {
                case DOWN:
                    cell = (Cell) getNodeByRowColumnIndex(pacman.getI() + 1, pacman.getJ(), gridPane);
                    if (cell.isBlocked)
                        break;
                    if (!(((PieceOfMap) cell).isCrossed())) {
                        score += 5;
                        crossed++;
                        ((PieceOfMap) cell).setCrossed(true);
                    }
                    pacman.moveDown();
                    swap(pacman, cell);
                    break;
                case UP:
                    cell = (Cell) getNodeByRowColumnIndex(pacman.getI() - 1, pacman.getJ(), gridPane);
                    if (cell.isBlocked)
                        break;
                    if (!(((PieceOfMap) cell).isCrossed())) {
                        score += 5;
                        crossed++;
                        ((PieceOfMap) cell).setCrossed(true);
                    }
                    pacman.moveUp();
                    swap(pacman, cell);
                    break;
                case LEFT:
                    cell = (Cell) getNodeByRowColumnIndex(pacman.getI(), pacman.getJ() - 1, gridPane);
                    if (cell.isBlocked)
                        break;
                    if (!(((PieceOfMap) cell).isCrossed())) {
                        score += 5;
                        crossed++;
                        ((PieceOfMap) cell).setCrossed(true);
                    }
                    pacman.moveLeft();
                    swap(pacman, cell);
                    break;
                case RIGHT:
                    cell = (Cell) getNodeByRowColumnIndex(pacman.getI(), pacman.getJ() + 1, gridPane);
                    if (cell.isBlocked)
                        break;
                    if (!(((PieceOfMap) cell).isCrossed())) {
                        crossed++;
                        score += 5;
                        ((PieceOfMap) cell).setCrossed(true);
                    }
                    pacman.moveRight();
                    swap(pacman, cell);
                    break;
            }
            updateScoreAndLife();
        });
        ghostTimeLine.getKeyFrames().add(ghostFrame);
        pacmanTimeLine = new Timeline();
        pacmanTimeLine.getKeyFrames().add(pacmanFrame);
        pacmanTimeLine.setCycleCount(Animation.INDEFINITE);
    }

    public Ghost getAccidentGhost() {
        for (Ghost ghost : ghosts) {
            if (ghost.getI() == pacman.getI() && ghost.getJ() == pacman.getJ())
                return ghost;
        }
        return null;
    }

    Integer[] generateRandomDirection() {
        ArrayList<Integer> randomNumberFrom1to4 = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            randomNumberFrom1to4.add(i + 1);
        for (int i = 0; i < 100; i++)
            Collections.shuffle(randomNumberFrom1to4);
        return randomNumberFrom1to4.toArray(new Integer[4]);
    }


    private void start() {
        crossed = 0;

        redGhost = new Ghost(GhostColor.RED);
        orangeGhost = new Ghost(GhostColor.ORANGE);
        pinkGhost = new Ghost(GhostColor.PINK);
        blueGhost = new Ghost(GhostColor.BLUE);

        pacman.setToGridPane(gridPane);

        ghosts = new ArrayList<>();
        ghosts.add(orangeGhost);
        ghosts.add(pinkGhost);
        ghosts.add(redGhost);
        ghosts.add(blueGhost);

        for (Ghost ghost : ghosts) {
            ghost.setToMap(gridPane);
        }
    }

    public void pause(ActionEvent actionEvent) {
        ghostTimeLine.stop();
        pacmanTimeLine.stop();

    }

    public void exit(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Main.stage.setTitle("Main Menu");
            Main.stage.setScene(new Scene(root, 300, 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
        savedGridPane = gridPane;
        ghostTimeLine.stop();
        pacmanTimeLine.stop();
    }

    public void lose() {
        if (life > 1) {
            life--;
            resetMap();
        } else {
            ghostTimeLine.stop();
            pacmanTimeLine.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            String context = "You lost :(\nYour score : " + score;
            if (score > User.onlineUser.getHighScore()) {
                User.onlineUser.setHighScore(score);
                DatabaseController.updateUser(User.onlineUser);
                context += "\nNew Record!";
            }
            alert.setContentText(context);
            map = null;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
                Main.stage.setTitle("Main Menu");
                Main.stage.setScene(new Scene(root, 300, 400));

            } catch (IOException e) {
                e.printStackTrace();
            }
            alert.show();
        }

    }

    public void updateScoreAndLife() {
        if (crossed == 228) {
            life++;
            resetMap();
        }
        lifeText.setText(String.valueOf(life));
        scoreText.setText(String.valueOf(score));

    }

    private void resetMap() {
        crossed = 0;
        for (Node child : gridPane.getChildren()) {
            if (child instanceof PieceOfMap && !((PieceOfMap) child).isBlocked) {
                ((PieceOfMap) child).setCrossed(false);
            }
        }
        pacman.reset(gridPane);
        for (Ghost ghost : ghosts) {
            ghost.reset(gridPane);
        }
        pacmanTimeLine.stop();
        ghostTimeLine.stop();
    }

    public static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    public static Node getGhostByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (node instanceof Ghost && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    public static void swap(Node n1, Node n2) {
//        Integer temp = GridPane.getRowIndex(n1);
        GridPane.setRowIndex(n1, GridPane.getRowIndex(n2));
//        GridPane.setRowIndex(n2, temp);
//
//        temp = GridPane.getColumnIndex(n1);
        GridPane.setColumnIndex(n1, GridPane.getColumnIndex(n2));
//        GridPane.setColumnIndex(n2, temp);
    }
}
