package ru.meshgroup.bankApplication.rest;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.security.JwtProvider;
import ru.meshgroup.bankApplication.utils.UserUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @Test
    void createUser() throws Exception {

        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);

        UserDto user = UserUtils.getRandomUserDto();

        mockMvc.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {

        mockMvc.perform(get("/api/user/1")
                        .contentType("application/json")
                        .header("authorization", "Bearer " + jwtProvider.generateToken("111")))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {

        mockMvc.perform(get("/api/user/")
                        .contentType("application/json")
                        .header("authorization", "Bearer " + jwtProvider.generateToken("11")))
                .andExpect(status().isOk());
    }

}