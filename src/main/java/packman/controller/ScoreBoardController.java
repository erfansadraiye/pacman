package packman.controller;

import packman.model.User;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardController {
    private static ScoreBoardController scoreBoardController;

    private ScoreBoardController() {

    }

    public static ScoreBoardController getInstance() {
        if (scoreBoardController == null)
            scoreBoardController = new ScoreBoardController();
        return scoreBoardController;
    }

    public ArrayList<User> sortedUsers() {
        ArrayList<User> allUser = DatabaseController.getAllUsers();
        allUser.sort(new SortByHighScore());
        return allUser;
    }

    static class SortByHighScore implements Comparator<User> {
        public int compare(User a, User b) {
            if (a.getHighScore() - b.getHighScore() != 0)
                return b.getHighScore() - a.getHighScore();
            else
                return a.getRecordTime().compareTo(b.getRecordTime());
        }
    }
}
