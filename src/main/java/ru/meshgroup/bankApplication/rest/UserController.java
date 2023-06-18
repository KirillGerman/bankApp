package ru.meshgroup.bankApplication.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.service.UserService;
import ru.meshgroup.bankApplication.utils.DatePattern;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Tag(name="1. User endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public Page<UserDto> getAllUsers(@RequestParam(name = "dateOfBirth", required = false)  @DatePattern  String dateOfBirth,
                                     @RequestParam(name = "phone", required = false) @Pattern(regexp = "^[0-9]{13}$") String phone,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "email", required = false) @Email String email,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size){
        return userService.getAllUsersDto(dateOfBirth, phone, name, email, page, size);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping( "/{userId}")
    public UserDto getUserById(@Parameter(example = "11") @PathVariable Long userId){
        return userService.getUserDtoById(userId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }


}
