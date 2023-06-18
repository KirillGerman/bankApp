package ru.meshgroup.bankApplication.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void test(){

        String dateOfBirth = "1986-04-08";
        String phone= null;
        String name= null;
        String email = null;
        Integer page = null;
        Integer size = null;

        userService.getAllUsersDto(dateOfBirth, phone, name, email, page, size);
    }
}