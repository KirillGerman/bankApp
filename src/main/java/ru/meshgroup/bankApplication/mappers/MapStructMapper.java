package ru.meshgroup.bankApplication.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.meshgroup.bankApplication.dto.AccountDto;
import ru.meshgroup.bankApplication.dto.EmailDataDto;
import ru.meshgroup.bankApplication.dto.PhoneDataDto;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.model.Account;
import ru.meshgroup.bankApplication.model.EmailData;
import ru.meshgroup.bankApplication.model.PhoneData;
import ru.meshgroup.bankApplication.model.User;

import java.util.List;


@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface MapStructMapper {

    UserDto userToUserDto(User user);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User userDtoToUser(UserDto user);

    AccountDto accountToAccountDto(Account user);

    Account accountDtoToAccount(AccountDto user);

    EmailDataDto emailToEmailDto(EmailData user);

    List<EmailDataDto> emailToEmailDto(List<EmailData> user);

    EmailData emailDtoToEmail(EmailDataDto user);

    PhoneDataDto phoneDataToPhoneDataDto(PhoneData user);

    PhoneData PhoneDataDtoToPhoneData(PhoneDataDto user);

    List<UserDto> userToUserDto(List<User> user);

    List<User> userDtoToUser(List<UserDto> user);


}
