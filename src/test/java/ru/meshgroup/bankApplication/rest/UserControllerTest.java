package ru.meshgroup.bankApplication.rest;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.meshgroup.bankApplication.dto.UserDto;
import ru.meshgroup.bankApplication.security.JwtProvider;
import ru.meshgroup.bankApplication.utils.UserUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@ExtendWith(PostgreSQLExtension.class)
@ActiveProfiles("dev")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @Test
    void shouldReturn200AndUserWhenUserCreated() throws Exception {
        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);
        UserDto user = UserUtils.getRandomUserDto();
        mockMvc.perform(post("/api/user/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturn200AndUserWhenGetRequestedByIdAndJWTProvided() throws Exception {
        mockMvc.perform(get("/api/user/1")
                        .contentType("application/json")
                        .header("authorization", "Bearer " + jwtProvider.generateToken("1")))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.account").isNotEmpty())
                .andExpect(jsonPath("$.emails").isArray())
                .andExpect(jsonPath("$.phones").isArray())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn401WhenGetRequestedByIdAndBadIdInJWT() throws Exception {
        mockMvc.perform(get("/api/user/1")
                        .contentType("application/json")
                        .header("authorization", "Bearer " + jwtProvider.generateToken("2")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn401WhenGetRequestedByIdAndNoJWT() throws Exception {
        mockMvc.perform(get("/api/user/1")
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn200AndAllUsersWhenGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/user/")
                        .contentType("application/json"))
                .andExpect(jsonPath("$.pageable").value("INSTANCE"))
                .andExpect(jsonPath("$.numberOfElements").isNumber())
                .andExpect(status().isOk());
    }

}