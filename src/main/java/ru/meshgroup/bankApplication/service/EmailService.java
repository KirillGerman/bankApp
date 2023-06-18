package ru.meshgroup.bankApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.meshgroup.bankApplication.dto.EmailDataAddDto;
import ru.meshgroup.bankApplication.dto.EmailDataDto;
import ru.meshgroup.bankApplication.dto.EmailDataUpdateDto;
import ru.meshgroup.bankApplication.exception.EmailExistsException;
import ru.meshgroup.bankApplication.mappers.MapStructMapper;
import ru.meshgroup.bankApplication.repository.EmailRepository;
import ru.meshgroup.bankApplication.utils.AuthUtils;

import javax.naming.NoPermissionException;
import javax.persistence.PersistenceException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final MapStructMapper mapper;

    public List<EmailDataDto> getEmailByUserId(Long userId) {
        return mapper.emailToEmailDto(emailRepository.findAllByUserId(userId));
    }

    public void deleteEmailsByUserId(Long userId) {
        emailRepository.deleteEmailByUserId(userId);
    }

    @Transactional
    public void deleteEmailById(Long userId, Long id) {
        var email = emailRepository.findById(id).orElseThrow(EmailExistsException::new);
        if (email.getUser().getId() != userId){
            throw new PermissionDeniedDataAccessException("Access denied", new RuntimeException());
        };
        emailRepository.deleteById(id);
    }

    @Transactional
    public List<EmailDataDto> postEmailById(Long userId, EmailDataAddDto emailDataDto) {
        emailRepository.findByEmail(emailDataDto.getEmail())
                .ifPresentOrElse(
                        email -> {throw new EmailExistsException("Email already exists");},
                        () -> emailRepository.insertEmailData(userId, emailDataDto.getEmail())
                );
        return mapper.emailToEmailDto(emailRepository.findAllByUserId(userId));
    }

    @Transactional
    public List<EmailDataDto> patchEmailById(Long userId, EmailDataUpdateDto emailUpdateDto) {
        var a = emailUpdateDto.getCurrentEmail();
        emailRepository.findByUserIdAndEmail(userId ,emailUpdateDto.getCurrentEmail())
                .ifPresentOrElse(
                        email -> email.setEmail(emailUpdateDto.getUpdateEmail()),
                        () -> {throw new EmailExistsException("Email not exists");}
                );
        return mapper.emailToEmailDto(emailRepository.findAllByUserId(userId));
    }

}
