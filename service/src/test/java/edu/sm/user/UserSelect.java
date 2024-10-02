package edu.sm.user;


import edu.sm.dto.User;

import edu.sm.service.UserService;

import java.util.List;

import java.util.List;

public class UserSelect {
    public static void main(String[] args) {
        UserService userService = new UserService();

        List<User> users = null;

        try {
            users = userService.get();

            // 테이블 헤더 출력
            System.out.printf("%-10s %-10s %-25s %-15s %-10s %-8s %-15s %-4s%n",
                    "User ID", "Name", "Email", "Phone", "Gender", "Age", "Joined", "Pwd");
            System.out.println("----------------------------------------------------------------------------");

            // 각 User 객체 출력
            for (User user : users) {
                System.out.printf("%-10s %-10s %-25s %-15s %-10s %-8d %-15s %-4s%n",
                        user.getUser_id(), user.getName(), user.getEmail(), user.getPhoneno(),
                        user.getGender() == 1 ? "Female" : "Male", user.getAge(), user.getJoined(), user.getPwd());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

