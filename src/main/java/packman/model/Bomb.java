package packman.model;

import javafx.scene.paint.Color;

public class Bomb extends PieceOfMap {

    public Bomb() {
        super(false);
        this.isCrossed = false;
        setFill(Color.WHITE);
    }

    public boolean isCrossed() {
        return isCrossed;
    }
}
