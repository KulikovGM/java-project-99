package hexlet.code.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hexlet.code.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.repository.UserRepository;
import hexlet.code.util.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

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
        var user = userRepository.findByEmail("hexlet@example.com")
                .orElseGet(() -> {
                    var newUser = new User();
                    newUser.setEmail("hexlet@example.com");
                    newUser.setPasswordDigest("password");
                    return userRepository.save(newUser);
                });
        token = getAuthToken(user);
    }

    protected String getAuthToken(User user) {
        var userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
        return "Bearer " + jwtUtil.generateToken(String.valueOf(userDetails));
    }


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/users").header("Authorization", token))
                .andExpect(status().isOk());
    }

//    @Test
//    public void testIndex2() throws Exception {
////        mockMvc.perform(get("/users"))
////                .andExpect(status().isOk());
//        var result = mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Тело JSON
//        var body = result.getResponse().getContentAsString();
//        assertThatJson(body).isArray();
//    }
}