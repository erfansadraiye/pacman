package packman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class PieceOfMap extends Cell {
    public boolean isCrossed;

    public PieceOfMap(boolean isBlocked) {
        this.isBlocked = isBlocked;
        this.isCrossed = isBlocked;
        if (isBlocked())
            setFill(Color.BLUE);
        else {
            Image image = new Image("file:src/main/resources/packman/view/is_not_crossed3.png");
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        }
    }

    public void setCrossed(boolean crossed) {
        isCrossed = crossed;
        if (isCrossed)
            setFill(Color.DARKGRAY);
        else {
            Image image = new Image("file:src/main/resources/packman/view/is_not_crossed3.png");
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        }
    }

    public boolean isCrossed() {
        return isCrossed;
    }
}
