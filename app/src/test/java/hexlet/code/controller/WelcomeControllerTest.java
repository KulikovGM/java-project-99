package hexlet.code.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Технически имя тестового метода не важно
    // Лучше использовать шаблон testНазваниеМетодаКонтроллера для основного сценария
    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk());
    }
}