package edu.sm.user;


import edu.sm.dto.User;

import edu.sm.service.UserService;

public class UserSelectOne {
    public static void main(String[] args) {
        UserService userService = new UserService();
        String id = "insung";
        User user = null;

        try {
            user = userService.get(id);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
