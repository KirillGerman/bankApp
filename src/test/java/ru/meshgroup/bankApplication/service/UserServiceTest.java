package ru.meshgroup.bankApplication.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import ru.meshgroup.bankApplication.utils.UserUtils;

import javax.transaction.Transactional;

@SpringBootTest
//@ActiveProfiles("dev")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    @Rollback(value = false)
    void test(){
        var user = UserUtils.getUserDto();
        user = userService.createUser(user);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void test1(){
        userService.createUser(UserUtils.getUserDto("test1@mail.com", "7123456789014"));
        userService.createUser(UserUtils.getUserDto("test2@mail.com", "7123456789015"));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void test2(){

        for (int i = 0; i < 10; i++) {
            userService.createUser(UserUtils.getRandomUserDto());
        }

        var a = userService.getAllUsers();

        System.out.println();
    }
}