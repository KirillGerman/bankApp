package ru.meshgroup.bankApplication.repository;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.model.User;
import ru.meshgroup.bankApplication.utils.UserUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@DataJpaTest
//@ActiveProfiles("test")
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create",
//})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Test
    @Transactional
    @Rollback(value = false)
    void test(){
        User user = UserUtils.getUser();
        userRepository.save(user);
        System.out.println(user);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void test1(){
        UserDto userDto = UserUtils.getRandomUserDto();
        var user = mapper.userDtoToUser(userDto);
        userRepository.save(user);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void test2() {
        var user = userRepository.findById(14L).get();
        System.out.println(user);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void test3(){
        var user = userRepository.findById(23L).get();
        user.getAccount().setBalance(new BigDecimal(1.1));
//        userRepository.save(user);
    }

}