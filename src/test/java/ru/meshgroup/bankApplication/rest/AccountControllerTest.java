package ru.meshgroup.bankApplication.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.meshgroup.bankApplication.security.JwtProvider;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @Test
    void transferTest() throws Exception {

        mockMvc.perform(post("/api/account/transfer/3")
                        .header("authorization", "Bearer " + jwtProvider.generateToken("test@mail.com"))
                        .content(objectMapper.writeValueAsString(new BigDecimal(10)))
                ).andExpect(status().isOk());
    }

}