package hexlet.code.component;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.taskstatus.TaskStatusCreateDTO;
import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.LabelService;
import hexlet.code.service.TaskService;
import hexlet.code.service.TaskStatusService;
import hexlet.code.service.UserService;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final TaskStatusService taskStatusService;
    private final UserService userService;
    private final TaskService taskService;
    private final LabelService labelService;
    private final Faker faker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createAdminUser();
        createTaskStatuses();
        createTasks();
        createLabels();
    }

    private void createAdminUser() {
        var email = "hexlet@example.com";

        var userData = new UserCreateDTO();
        userData.setFirstName("Admin");
        userData.setLastName("Admin");
        userData.setEmail(email);
        userData.setPassword("qwerty");
        userService.create(userData);
    }

    private void createTaskStatuses() {
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

    private void createTasks() {
        var user = userRepository.findByEmail("hexlet@example.com")
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

        List.of("draft", "to_review", "to_be_fixed", "to_publish", "published")
                .forEach(status ->
                        IntStream.range(1, 5).forEach(i -> {
                            var task = new TaskCreateDTO();
                            task.setIndex(faker.number().numberBetween(1, 1000));
                            task.setAssigneeId(user.getId());
                            task.setTitle(faker.name().title());
                            task.setContent(faker.hobbit().quote());
                            task.setStatus(status);
                            taskService.create(task);
                        })
            );
    }

    private void createLabels() {
        List.of("feature", "bug").forEach(name -> {
            var label = new LabelCreateDTO();
            label.setName(name);
            labelService.createLabel(label);
        });
    }
}
