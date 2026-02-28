package hexlet.code.component;

import hexlet.code.dto.taskstatus.TaskStatusCreateDTO;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.model.User;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.TaskStatusService;
import lombok.AllArgsConstructor;
//import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TaskStatusRepository taskStatusRepository;
    @Autowired
    private TaskStatusMapper taskStatusMapper;
    @Autowired
    private TaskMapper taskMapper;
    private final TaskStatusService taskStatusService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";
        var userData = new User();
        userData.setEmail(email);
        userData.setPasswordDigest(passwordEncoder.encode("qwerty"));
        userData.setFirstName("admin");
        userData.setLastName("admin");
        userRepository.save(userData);

        var taskStatusData = Map.of(
                "Draft", "draft",
                "ToReview", "to_review",
                "ToBeFixed", "to_be_fixed",
                "ToPublish", "to_publish",
                "Published", "published"
        );

        taskStatusData.forEach((key, value) -> {
            var taskStatus = new TaskStatusCreateDTO();
            taskStatus.setName(key);
            taskStatus.setSlug(value);
            taskStatusService.create(taskStatus);
        });
    }
}
