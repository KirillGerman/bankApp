package ru.meshgroup.bankApplication.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.EmailDataDto;
import ru.meshgroup.bankApplication.dto.EmailUpdateDto;
import ru.meshgroup.bankApplication.service.EmailService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/{userId}")
    public EmailDataDto getEmailByUserId(@PathVariable Long userId) {
        return emailService.getEmailById(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmailByUserId(@PathVariable Long userId) {
        emailService.deleteEmailById(userId);
    }

    @PatchMapping("/{userId}")
    public List<EmailDataDto> patchEmailByUserId(@PathVariable Long userId, @Valid @RequestBody EmailUpdateDto emailData) {
       return emailService.patchEmailById(userId, emailData);
    }

}
