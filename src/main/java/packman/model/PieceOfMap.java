package packman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PieceOfMap extends Rectangle {
    private boolean isBlocked;
    private boolean isCrossed;
    private Image image;

    public PieceOfMap(boolean isBlocked) {
        super(50, 50);
        this.isBlocked = isBlocked;
        this.isCrossed = isBlocked;
        if (isBlocked())
            setFill(Color.BLUE);
        else {
            setFill(Color.DARKGRAY);

        }
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
