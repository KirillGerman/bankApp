package ru.meshgroup.bankApplication.utils;

import ru.meshgroup.bankApplication.dto.AccountDto;
import ru.meshgroup.bankApplication.dto.EmailDataDto;
import ru.meshgroup.bankApplication.dto.PhoneDataDto;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.model.Account;
import ru.meshgroup.bankApplication.model.EmailData;
import ru.meshgroup.bankApplication.model.PhoneData;
import ru.meshgroup.bankApplication.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UserUtils {


    public static User getUser() {
        return getUser("test@mail.com", "7123456789012");
    }

    public static User getRandomUser() {
        return getUser("test" + (long) (Math.random()*Math.pow(12,12)) + "@mail.com", "" + (long) (Math.random()*Math.pow(12,12)));
    }

    public static UserDto getUserDto() {
        return getUserDto("test@mail.com", "7123456789012");
    }

    public static UserDto getRandomUserDto() {
        return getUserDto("test" + (long) (Math.random()*Math.pow(12,12)) + "@mail.com", "" + (long) (Math.random()*Math.pow(12,12)));
    }

    public static User getUser(String email, String phone) {

        Account account = new Account(new BigDecimal(100.10));
        EmailData emailData = new EmailData(email);
        PhoneData phoneData = new PhoneData(phone);

        return User.builder()
                .name("Ivan")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .password("123456789")
                .account(account)
                .emails(List.of(emailData))
                .phones(List.of(phoneData))
                .build();
    }

    public static UserDto getUserDto(String email, String phone) {

        AccountDto account = new AccountDto(new BigDecimal(100.10));
        EmailDataDto emailData = new EmailDataDto(email);
        PhoneDataDto phoneData = new PhoneDataDto(phone);

        return UserDto.builder()
                .name("Ivan")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .password("123456789")
                .account(account)
                .emails(List.of(emailData))
                .phones(List.of(phoneData))
                .build();

    }
}
