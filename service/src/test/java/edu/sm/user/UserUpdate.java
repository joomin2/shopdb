package edu.sm.user;



import edu.sm.dto.User;

import edu.sm.service.UserService;

public class UserUpdate {
    public static void main(String[] args) throws Exception {
        UserService userService= new UserService();
        User user = User.builder()
                .user_id("insung")
                .name("곽인성")
                .email("kis2690@gmail.com")
                .pwd("2690")
                .phoneno("01047072690")
                .gender(0)
                .age(25)

                .build();

        userService.modify(user);




    }
}
