package packman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.net.MalformedURLException;

public class PieceOfMap extends Rectangle {
    private boolean isBlocked;
    private boolean isCrossed;

    public PieceOfMap(boolean isBlocked) {
        super(37, 37);
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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setCrossed(boolean crossed) {
        isCrossed = crossed;
        setFill(Color.DARKGRAY);
    }

    public boolean isCrossed() {
        return isCrossed;
    }
}
