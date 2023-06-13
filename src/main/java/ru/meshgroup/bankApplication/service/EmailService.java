package ru.meshgroup.bankApplication.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.bankApplication.dto.EmailDataDto;
import ru.meshgroup.bankApplication.dto.EmailUpdateDto;
import ru.meshgroup.bankApplication.exception.EmailExistsException;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.repository.EmailRepository;

import java.util.List;

import java.util.Optional;

import static ru.meshgroup.bankApplication.utils.ServiceUtils.checkAllParamsNonNull;


@AllArgsConstructor
@Component
public class EmailService {

    private final EmailRepository emailRepository;
    private final MapStructMapper mapper;

    public EmailDataDto getEmailById(Long userId) {
        return mapper.emailToEmailDto(emailRepository.findById(userId).orElseThrow(EmailExistsException::new));
    }

    public void deleteEmailById(Long userId) {
        emailRepository.deleteById(userId);
    }

    @Transactional
    public List<EmailDataDto> patchEmailById(Long userId, EmailUpdateDto emailUpdateDto){
        return Optional.ofNullable(emailUpdateDto.getEmail())
                .map(mail -> addEmailById(userId, emailUpdateDto))
                .orElseGet(() -> {
                            checkAllParamsNonNull(emailUpdateDto.getCurrentEmail(), emailUpdateDto.getUpdateEmail());
                            return editEmailById(userId, emailUpdateDto);
                });
    };

    public List<EmailDataDto> addEmailById(Long userId, EmailUpdateDto emailDataDto) {
        emailRepository.findByEmail(emailDataDto.getEmail())
                .ifPresentOrElse(
                        email -> {throw new EmailExistsException("Email exists", new EmailDataDto(emailDataDto.getEmail()));},
                        () -> emailRepository.insertEmailData(userId, emailDataDto.getEmail())
                );
        return mapper.emailToEmailDto(emailRepository.findAllByUserId(userId));
    }

    public List<EmailDataDto> editEmailById(Long userId, EmailUpdateDto emailUpdateDto) {
        emailRepository.findByEmailAndUserId(userId ,emailUpdateDto.getCurrentEmail())
                .ifPresentOrElse(
                        email -> email.setEmail(emailUpdateDto.getUpdateEmail()),
                        () -> {throw new EmailExistsException("Email not exists ", new EmailDataDto(emailUpdateDto.getCurrentEmail()));}
                );
        return mapper.emailToEmailDto(emailRepository.findAllByUserId(userId));
    }

}
