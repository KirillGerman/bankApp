package ru.meshgroup.bankApplication.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.service.UserService;
import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public Page<UserDto> getAllUsers(@RequestParam(name = "dateOfBirth", required = false) String dateOfBirth,
                                     @RequestParam(name = "phone", required = false) String phone,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "email", required = false) String email,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size){
        return userService.getAllUsers(dateOfBirth, phone, name, email, page, size);
    }

    @GetMapping( "/{userId}")
    public UserDto getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/")
    public UserDto createUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @DeleteMapping( "/{userId}")
    public UserDto deleteUserById(@PathVariable Long userId){
        return null;
    }

    @DeleteMapping("/")
    public UserDto deleteAllUsers(){
        return null;
    }

    @PutMapping( "/{userId}")
    public UserDto updateUser(@Valid @RequestBody UserDto userId){
        return null;
    }

}
