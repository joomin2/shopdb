package edu.sm.user;


import edu.sm.dto.User;

import edu.sm.service.UserService;

public class Userinsert {
    public static void main(String[] args) throws Exception {


        UserService userService = new UserService();
        User user = User.builder()
                .user_id("insung")
                .name("인성")
                .email("kis2690@naver.com")
                .pwd("1234")
                .phoneno("01012345678")
                .gender(0)
                .age(25)


                .build();


            userService.add(user);


    }
}
