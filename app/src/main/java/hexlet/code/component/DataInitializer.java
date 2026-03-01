package hexlet.code.component;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.taskstatus.TaskStatusCreateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.model.User;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.LabelService;
import hexlet.code.service.TaskService;
import hexlet.code.service.TaskStatusService;
import hexlet.code.service.UserService;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

    private final UserService userService;
    private final TaskService taskService;
    private final LabelService labelService;
    private final Faker faker;

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

        // Task
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        taskStatusData.values().forEach(v ->
                IntStream.range(1, 5).forEach(i -> {
                    var task = new TaskCreateDTO();
                    task.setIndex(faker.number().numberBetween(1, 1000));
                    task.setAssigneeId(user.getId());
                    task.setTitle(faker.name().title());
                    task.setContent(faker.hobbit().quote());
                    task.setStatus(v);
                    taskService.create(task);
                })
        );

        // Label
        var labelData = List.of(
                "feature",
                "bug"
        );

        labelData.forEach(l -> {
            var label = new LabelCreateDTO();
            label.setName(l);
            labelService.createLabel(label);
        });
    }

}
