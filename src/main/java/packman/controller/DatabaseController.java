package packman.controller;

import com.google.gson.Gson;
import packman.model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseController {

    private static String getUserDirectory(String username) {
        return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "packman" + File.separator + "users" + File.separator + username + ".json";
    }

    public static User getUserByUsername(String username) {
        String dir = getUserDirectory(username);
        try {
            FileReader fileReader = new FileReader(dir);
            Gson gson = new Gson();
            User user = gson.fromJson(fileReader, User.class);
            fileReader.close();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteUser(String username) {
        File file = new File(getUserDirectory(username));
        file.delete();
    }

    public static boolean isUserExisted(String username) {
        String dir = getUserDirectory(username);
        File file = new File(dir);
        return file.exists();
    }

    public static void updateUser(User user) {
        String username = user.getUsername();
        try {
            FileWriter fileWriter = new FileWriter(getUserDirectory(username));
            Gson gson = new Gson();
            gson.toJson(user, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        File folder = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "packman" + File.separator + "users");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getAbsolutePath().endsWith(".json")) {
                    String username = file.getName().substring(0, file.getName().length() - 5);
                    users.add(getUserByUsername(username));
                }
            }
        }
        return users;
    }

}
