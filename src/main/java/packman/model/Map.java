package packman.model;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map {

    final int HEIGHT = 10;
    final int WIDTH = 10;
    final int LENGTH = 10;
    final int HEIGHT_OF_WALL = 2 * HEIGHT + 1;
    final int WIDTH_OF_WALL = 2 * WIDTH + 1;
    private int[][] maze;

    public Map() {
        maze = new int[LENGTH * 2 + 1][LENGTH * 2 + 1];
        run();
    }

    void run() {
        int widthPositionInMaze = 1;
        int widthPositionInIsCrossed = 0;
        int heightPositionInMaze = 1;
        int heightPositionInIsCrossed = 0;
        boolean[][] isCrossed = new boolean[HEIGHT][WIDTH];
        createFullBlockedMaze(isCrossed);
        setRandomDirection(heightPositionInMaze, widthPositionInMaze, heightPositionInIsCrossed, widthPositionInIsCrossed, isCrossed);
        for (int i = 0; i < 3 * WIDTH; i++) {
            Random random = new Random();
            int upper = WIDTH_OF_WALL - 2;
            int iRandom = random.nextInt(upper) + 1;
            int jRandom = random.nextInt(upper) + 1;
            if (((iRandom % 2 == 1 && jRandom % 2 == 0) || (iRandom % 2 == 0 && jRandom % 2 == 1)) && maze[iRandom][jRandom] == 1)
                maze[iRandom][jRandom] = 0;
            else
                i--;
        }
    }

    private void createMap(GridPane gridPane) {
        for (int i = 0; i < HEIGHT_OF_WALL; i++) {
            for (int j = 0; j < WIDTH_OF_WALL; j++) {
                PieceOfMap pieceOfMap;
                if (maze[i][j] == 1)
                    pieceOfMap = new PieceOfMap(true);
                else
                    pieceOfMap = new PieceOfMap(false);
                GridPane.setRowIndex(pieceOfMap, i);
                GridPane.setColumnIndex(pieceOfMap, j);
                gridPane.getChildren().add(pieceOfMap);
            }
        }
    }


    public void passGridPaneChildren(GridPane gridPane) {
        createMap(gridPane);
    }

    private void createFullBlockedMaze(boolean[][] isCrossed) {
        for (int i = 0; i < HEIGHT_OF_WALL; i++) {
            for (int j = 0; j < WIDTH_OF_WALL; j++) {
                if (i % 2 == 1 && j % 2 == 1)
                    maze[i][j] = -1;
                else
                    maze[i][j] = 1;
            }
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                isCrossed[i][j] = false;
            }
        }
        isCrossed[0][0] = true;
    }

    boolean moveLeftIfItsPossible(int heightPositionInMaze, int widthPositionInMaze, int heightPositionInIsCrossed, int widthPositionInIsCrossed, boolean[][] isCrossed) {
        boolean isMovePossible = widthPositionInIsCrossed - 1 >= 0 && !isCrossed[heightPositionInIsCrossed][widthPositionInIsCrossed - 1];
        if (isMovePossible) {
            maze[heightPositionInMaze][widthPositionInMaze - 1] = 0;
            isCrossed[heightPositionInIsCrossed][widthPositionInIsCrossed - 1] = true;
            return true;
        }
        return false;
    }

    void setRandomDirection(int heightPositionInMaze, int widthPositionInMaze, int heightPositionInIsCrossed, int widthPositionInIsCrossed, boolean[][] isCrossed) {
        Integer[] direction = generateRandomDirection();

        for (Integer integer : direction) {
            switch (integer) {
                case 1://Right
                    if (moveRightIfItsPossible(heightPositionInMaze, widthPositionInMaze, heightPositionInIsCrossed, widthPositionInIsCrossed, isCrossed))
                        setRandomDirection(heightPositionInMaze, widthPositionInMaze + 2, heightPositionInIsCrossed, widthPositionInIsCrossed + 1, isCrossed);
                    break;
                case 2://Down
                    if (moveDownIfItsPossible(heightPositionInMaze, widthPositionInMaze, heightPositionInIsCrossed, widthPositionInIsCrossed, isCrossed))
                        setRandomDirection(heightPositionInMaze + 2, widthPositionInMaze, heightPositionInIsCrossed + 1, widthPositionInIsCrossed, isCrossed);
                    break;
                case 3://Up
                    if (moveUpIfItsPossible(heightPositionInMaze, widthPositionInMaze, heightPositionInIsCrossed, widthPositionInIsCrossed, isCrossed))
                        setRandomDirection(heightPositionInMaze - 2, widthPositionInMaze, heightPositionInIsCrossed - 1, widthPositionInIsCrossed, isCrossed);
                    break;
                case 4://Left
                    if (moveLeftIfItsPossible(heightPositionInMaze, widthPositionInMaze, heightPositionInIsCrossed, widthPositionInIsCrossed, isCrossed))
                        setRandomDirection(heightPositionInMaze, widthPositionInMaze - 2, heightPositionInIsCrossed, widthPositionInIsCrossed - 1, isCrossed);
                    break;
            }
        }

    }

    boolean moveRightIfItsPossible(int heightPositionInMaze, int widthPositionInMaze, int heightPositionInIsCrossed, int widthPositionInIsCrossed, boolean[][] isCrossed) {
        boolean isMovePossible = widthPositionInIsCrossed + 1 < WIDTH && !isCrossed[heightPositionInIsCrossed][widthPositionInIsCrossed + 1];
        if (isMovePossible) {
            maze[heightPositionInMaze][widthPositionInMaze + 1] = 0;
            isCrossed[heightPositionInIsCrossed][widthPositionInIsCrossed + 1] = true;
            return true;
        }
        return false;

    }

    boolean moveDownIfItsPossible(int heightPositionInMaze, int widthPositionInMaze, int heightPositionInIsCrossed, int widthPositionInIsCrossed, boolean[][] isCrossed) {
        boolean isMovePossible = heightPositionInIsCrossed + 1 < HEIGHT && !isCrossed[heightPositionInIsCrossed + 1][widthPositionInIsCrossed];
        if (isMovePossible) {
            maze[heightPositionInMaze + 1][widthPositionInMaze] = 0;
            isCrossed[heightPositionInIsCrossed + 1][widthPositionInIsCrossed] = true;
            return true;
        }
        return false;
    }

    boolean moveUpIfItsPossible(int heightPositionInMaze, int widthPositionInMaze, int heightPositionInIsCrossed, int widthPositionInIsCrossed, boolean[][] isCrossed) {
        boolean isMovePossible = heightPositionInIsCrossed - 1 >= 0 && !isCrossed[heightPositionInIsCrossed - 1][widthPositionInIsCrossed];
        if (isMovePossible) {
            maze[heightPositionInMaze - 1][widthPositionInMaze] = 0;
            isCrossed[heightPositionInIsCrossed - 1][widthPositionInIsCrossed] = true;
            return true;
        }
        return false;
    }

    Integer[] generateRandomDirection() {
        ArrayList<Integer> randomNumberFrom1to4 = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            randomNumberFrom1to4.add(i + 1);
        for (int i = 0; i < 100; i++)
            Collections.shuffle(randomNumberFrom1to4);
        return randomNumberFrom1to4.toArray(new Integer[4]);
    }
}
