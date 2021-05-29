package packman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Packman {
    int i;
    int j;
    Direction direction;
    ImagePattern imagePattern;

    public Packman() {
       setDirectionRight();
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

    public void setJ(int j) {
        this.j = j;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setDirectionRight() {
        this.direction = Direction.RIGHT;
        Image image = new Image("file:src/main/resources/packman/view/pacman_right.gif");
        imagePattern = new ImagePattern(image);
    }
    public void setDirectionLeft() {
        this.direction = Direction.LEFT;
        Image image = new Image("file:src/main/resources/packman/view/pacman_lef.gif");
        imagePattern = new ImagePattern(image);
    }
    public void setDirectionUp() {
        this.direction = Direction.UP;
        Image image = new Image("file:src/main/resources/packman/view/pacman_up.gif");
        imagePattern = new ImagePattern(image);
    }
    public void setDirectionDown() {
        this.direction = Direction.DOWN;
        Image image = new Image("file:src/main/resources/packman/view/pacman_down.gif");
        imagePattern = new ImagePattern(image);
    }
}
