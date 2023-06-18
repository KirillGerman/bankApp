package ru.meshgroup.bankApplication.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="5. Email endpoints")
@AllArgsConstructor
@RestController
@RequestMapping("/api/phone")
public class PhoneController {
}
