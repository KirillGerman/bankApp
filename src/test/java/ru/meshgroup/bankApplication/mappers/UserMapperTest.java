package ru.meshgroup.bankApplication.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.utils.UserUtils;

import java.time.LocalDate;


class UserMapperTest {

    MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Test
    void test(){

        UserDto userDto = new UserDto();
        userDto.setName("Ivan");
        userDto.setDateOfBirth(LocalDate.of(2020,8,15));
        userDto.setPassword("1234568");

        var user = mapper.userDtoToUser(userDto);

        System.out.println(user);

    }

    @Test
    void test1(){

        UserDto userDto = UserUtils.getUserDto();
        var user = mapper.userDtoToUser(userDto);
        System.out.println(user);

    }
}
