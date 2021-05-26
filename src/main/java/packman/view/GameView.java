package packman.view;

import javafx.scene.layout.GridPane;
import packman.model.Map;

public class GameView {

    public GridPane gridPane;

    public void initialize() {
        Map map = new Map();
        map.passGridPaneChildren(gridPane);
    }

}
