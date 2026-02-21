package hexlet.code.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Технически имя тестового метода не важно
    // Лучше использовать шаблон testНазваниеМетодаКонтроллера для основного сценария
    @Test
    public void testIndex() throws Exception {
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk());
        var result = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andReturn();

        // Тело это строка, в этом случае JSON
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }
}