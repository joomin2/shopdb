package edu.sm.user;


import edu.sm.service.UserService;

public class UserDelete {
    public static void main(String[] args) {
        UserService userService = new UserService();
        String id = "insung";

        try {
            userService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
