package ru.meshgroup.bankApplication.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.bankApplication.dto.EmailDataAddDto;
import ru.meshgroup.bankApplication.dto.EmailDataDto;
import ru.meshgroup.bankApplication.dto.EmailDataUpdateDto;
import ru.meshgroup.bankApplication.service.EmailService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name="4. Email endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{userId}")
    public List<EmailDataDto> getEmailByUserId(@Parameter(example = "11") @PathVariable Long userId) {
        return emailService.getEmailByUserId(userId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{userId}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteEmailById(@PathVariable @NotNull Long userId, @PathVariable @NotNull Long id) {
        emailService.deleteEmailById(userId, id);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<EmailDataDto> postEmail(@Parameter(example = "11") @PathVariable  @NotNull  Long userId, @Valid @RequestBody EmailDataAddDto emailData) {
        return emailService.postEmailById(userId, emailData);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/{userId}")
    public List<EmailDataDto> patchEmail(@Parameter(example = "11") @PathVariable  @NotNull  Long userId, @Valid @RequestBody EmailDataUpdateDto emailData) {
       return emailService.patchEmailById(userId, emailData);
    }

}
