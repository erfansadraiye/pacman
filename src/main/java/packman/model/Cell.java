package packman.model;

import javafx.scene.shape.Rectangle;

public abstract class Cell extends Rectangle {
    public boolean isBlocked;
    public Cell(){
        super(36,36);
        isBlocked =  true;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
