package packman.controller;

import packman.model.User;

public class LoginController {
    private static LoginController loginController;

    private LoginController() {

    }

    public static LoginController getInstance() {
        if (loginController == null)
            loginController = new LoginController();
        return loginController;
    }

    public void createUser(String username, String password) throws Exception {
        if (!(username.matches("\\w+") && password.matches("\\w+")))
            throw new Exception("username/password is invalid.");
        if (DatabaseController.isUserExisted(username))
            throw new Exception(String.format("%s is duplicated.", username));
        DatabaseController.updateUser(new User(username, password));
    }

    public void loginUser(String username, String password) throws Exception {
        if (!DatabaseController.isUserExisted(username))
            throw new Exception(String.format("%s is not existed.", username));
        if (!(DatabaseController.getUserByUsername(username).isPasswordTrue(password)))
            throw new Exception("username/password is incorrect.");
        User.onlineUser = DatabaseController.getUserByUsername(username);
    }
}
