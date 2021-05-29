package packman.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import packman.view.GameView;

import java.util.Random;

public class Pacman extends Cell {
    int i;
    int j;
    int baseI;
    int baseJ;
    Direction direction;
    ImagePattern imagePattern;

    public Pacman(GridPane gridPane) {
        setDirectionRight();
        setIAndJ(gridPane);
    }

    public int getBaseI() {
        return baseI;
    }

    public void setBaseI(int baseI) {
        this.baseI = baseI;
    }

    public int getBaseJ() {
        return baseJ;
    }

    public void setBaseJ(int baseJ) {
        this.baseJ = baseJ;
    }

    public void setIAndJ(GridPane gridPane) {
        Random random = new Random();
        int row;
        int column;
        do {
            row = random.nextInt(3) + 9;
            setI(row);
            setBaseI(row);
            column = random.nextInt(3) + 9;
            setJ(column);
            setBaseJ(column);
        } while (((PieceOfMap) GameView.getNodeByRowColumnIndex(row, column, gridPane)).isBlocked());

    }

    public void setToGridPane(GridPane gridPane) {
        Node node = GameView.getNodeByRowColumnIndex (i, j, gridPane);
        int ii = GridPane.getRowIndex(node);
        int jj = GridPane.getColumnIndex(node);
        GridPane.setRowIndex(this, ii);
        GridPane.setColumnIndex(this, jj);
        ((PieceOfMap)node).setCrossed(true);
        if (!gridPane.getChildren().contains(this))
            gridPane.getChildren().add(this);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void moveRight() {
        j++;
    }

    public void moveLeft() {
        j--;
    }

    public void moveUp() {
        i--;
    }

    public void moveDown() {
        i++;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirectionRight() {
        this.direction = Direction.RIGHT;
        Image image = new Image("file:src/main/resources/packman/view/pacman_right.gif");
        imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }

    public void setDirectionLeft() {
        this.direction = Direction.LEFT;
        Image image = new Image("file:src/main/resources/packman/view/pacman_left.gif");
        imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }

    public void setDirectionUp() {
        this.direction = Direction.UP;
        Image image = new Image("file:src/main/resources/packman/view/pacman_up.gif");
        imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }

    public void setDirectionDown() {
        this.direction = Direction.DOWN;
        Image image = new Image("file:src/main/resources/packman/view/pacman_down.gif");
        imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }

    public void reset(GridPane gridPane) {
        setI(baseI);
        setJ(baseJ);
        setToGridPane(gridPane);
    }
}
