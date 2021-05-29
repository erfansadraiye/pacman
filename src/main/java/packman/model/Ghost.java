package packman.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import packman.view.GameView;

public class Ghost extends Cell {
    int i;
    int j;
    int baseI;
    int baseJ;
    ImagePattern imagePattern;
    String defaultColor;

    public Ghost(GhostColor ghostColor) {
        switch (ghostColor) {
            case RED:
                defaultColor = "file:src/main/resources/packman/view/red_ghost.gif";
                i = 1;
                baseI = 1;
                baseJ = 1;
                j = 1;
                break;
            case BLUE:
                defaultColor = "file:src/main/resources/packman/view/blue_ghost.gif";
                baseI = 19;
                i = 19;
                baseJ = 19;
                j = 19;
                break;
            case ORANGE:
                defaultColor = "file:src/main/resources/packman/view/orange_ghost.gif";
                baseI = 1;
                i = 1;
                baseJ = 19;
                j = 19;
                break;
            case PINK:
                defaultColor = "file:src/main/resources/packman/view/pink_ghost.gif";
                i = 19;
                baseI = 19;
                baseJ = 1;
                j = 1;
                break;
            default:
                defaultColor = "";
        }
        imagePattern = new ImagePattern(new Image(defaultColor));
        setFill(imagePattern);
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

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setToMap(GridPane gridPane) {
        GridPane.setRowIndex(this, i);
        GridPane.setColumnIndex(this, j);
        if (!gridPane.getChildren().contains(this))
            gridPane.getChildren().add(this);
    }

    public void setScared() {
        imagePattern = new ImagePattern(new Image("file:src/main/resources/packman/view/scared_ghost.gif"));
        setFill(imagePattern);
    }

    public void setDefault() {
        imagePattern = new ImagePattern(new Image(defaultColor));
        setFill(imagePattern);
    }

    public void reset(GridPane gridPane) {
        setI(baseI);
        setJ(baseJ);
        setToMap(gridPane);
    }
}
