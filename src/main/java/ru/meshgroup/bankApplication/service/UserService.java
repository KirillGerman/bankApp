package ru.meshgroup.bankApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.exception.UserNotFoundException;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.model.User;
import ru.meshgroup.bankApplication.repository.UserRepository;
import java.util.List;

import static ru.meshgroup.bankApplication.utils.ServiceUtils.getPageSize;
import static ru.meshgroup.bankApplication.utils.ServiceUtils.getUserSpecification;


@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapStructMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public List<UserDto> getAllUsersDto(){
        return mapper.userToUserDto(userRepository.findAll());
    }

    @Transactional
    public Page<UserDto> getAllUsersDto(String dateOfBirth, String phone, String name, String email , Integer page, Integer size){
        var userDtoList = userRepository.findAll(
                getUserSpecification(dateOfBirth, phone, name, email),
                getPageSize(page, size)
        );
        return new PageImpl<UserDto>(
                mapper.userToUserDto(userDtoList.toList()),
                getPageSize(page, size),
                userDtoList.getTotalPages()
        );
    }

    @Transactional
    public UserDto getUserDtoById(Long userId){
        return mapper.userToUserDto(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserDto createUser(UserDto userDto){
        return mapper.userToUserDto(userRepository.save(mapper.userDtoToUser(userDto)));
    }

}
