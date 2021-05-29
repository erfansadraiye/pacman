package packman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Ghost {
    int i;
    int j;
    ImagePattern imagePattern;
    String defaultColor;

    public Ghost(GhostColor ghostColor) {
        switch (ghostColor) {
            case RED:
                defaultColor = "file:src/main/resources/packman/view/red_ghost.gif";
                break;
            case BLUE:
                defaultColor = "file:src/main/resources/packman/view/blue_ghost.gif";
                break;
            case ORANGE:
                defaultColor = "file:src/main/resources/packman/view/orange_ghost.gif";
                break;
            case PINK:
                defaultColor = "file:src/main/resources/packman/view/pink_ghost.gif";
                break;
            default:
                defaultColor = "";
        }
        imagePattern = new ImagePattern(new Image(defaultColor));
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

    public void setScared() {
        imagePattern = new ImagePattern(new Image( "file:src/main/resources/packman/view/scared_ghost.gif"));
    }

    public void setDefault(){
        imagePattern = new ImagePattern(new Image(defaultColor));
    }
}
