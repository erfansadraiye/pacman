package packman.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {
    public static User onlineUser;
    public int numberOfLife;
    public String levelOfGame;
    private String username;
    private String password;
    private int highScore;
    private String recordTime;
    private ArrayList<Map> maps;
    private int indexOfSelectedMap;

    public User(String username, String password) {
        setPassword(password);
        setUsername(username);
        setHighScore(0);
        maps = new ArrayList<>();
        indexOfSelectedMap = 0;
        for (int i = 0; i < 5; i++) {
            maps.add(new Map());
        }
        levelOfGame = "Easy";
        numberOfLife = 3;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public int getIndexOfSelectedMap() {
        return indexOfSelectedMap;
    }

    public void setIndexOfSelectedMap(int indexOfSelectedMap) {
        this.indexOfSelectedMap = indexOfSelectedMap;
    }

    public void increaseIndexOfSelectedMap() {
        this.indexOfSelectedMap++;
    }

    public void decreaseIndexOfSelectedMap() {
        this.indexOfSelectedMap--;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public String getLevelOfGame() {
        return levelOfGame;
    }

    public void setLevelOfGame(String levelOfGame) {
        this.levelOfGame = levelOfGame;
    }

    public int getNumberOfLife() {
        return numberOfLife;
    }

    public void setNumberOfLife(int numberOfLife) {
        this.numberOfLife = numberOfLife;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        recordTime = dtf.format(now);
    }

    public boolean isPasswordTrue(String password) {
        return getPassword().equals(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int numberOfMaps() {
        return maps.size();
    }
}
