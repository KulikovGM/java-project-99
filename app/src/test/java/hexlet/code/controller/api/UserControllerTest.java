package hexlet.code.controller.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hexlet.code.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hexlet.code.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.util.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper om;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected JWTUtils jwtUtil;

    protected String token;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();

        var user = new User();
        user.setEmail("hexlet@example.com");
        user.setPasswordDigest(passwordEncoder.encode("qwerty"));
        userRepository.save(user);

        token = getAuthToken(user);
    }

    protected String getAuthToken(User user) {;
        return "Bearer " + jwtUtil.generateToken(user.getEmail());
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testIndexWithoutAuth() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isUnauthorized()); // 401
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/users").header("Authorization", token))
                .andExpect(status().isOk());
    }

}