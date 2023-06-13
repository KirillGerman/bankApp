package ru.meshgroup.bankApplication.exception;

import ru.meshgroup.bankApplication.dto.UserDto;

public class UserNotFoundException extends RuntimeException{

    private UserDto userDto;

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message, UserDto userDto) {
        super(message);
        this.userDto = userDto;
    }

    public UserNotFoundException(UserDto userDto) {
        super("User not found");
        this.userDto = userDto;
    }
}
