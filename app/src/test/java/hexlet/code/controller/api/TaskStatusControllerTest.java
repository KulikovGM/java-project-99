package hexlet.code.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.TaskStatus;

import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.util.JWTUtils;
import hexlet.code.util.ModelGenerator;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskStatusControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    protected ObjectMapper om;

    @Autowired
    protected TaskStatusRepository statusRepository;

    @Autowired
    protected JWTUtils jwtUtil;

    @Autowired
    private ModelGenerator modelGenerator;
    private TaskStatus testTaskStatus;
    protected String token;

    @BeforeEach
    public void setUp() {
        statusRepository.deleteAll();

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .apply(springSecurity()).build();

        testTaskStatus = Instancio.of(modelGenerator.getTaskStatusModel()).create();
        statusRepository.save(testTaskStatus);
    }


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/task_statuses").header("Authorization", token))
                .andExpect(status().isOk());
    }
}
