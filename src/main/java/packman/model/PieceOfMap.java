package packman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PieceOfMap extends Rectangle {
    private boolean isBlocked;
    private boolean isCrossed;
    private Image image;

    public PieceOfMap(boolean isBlocked) {
        super(36, 36);
        this.isBlocked = isBlocked;
        this.isCrossed = isBlocked;
        if (isBlocked())
            setFill(Color.BLUE);
        else {
            Image image = new Image("src/main/resources/packman/view/is_not_crossed.png");
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        }
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
