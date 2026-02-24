package hexlet.code.controller.api;

import hexlet.code.dto.taskstatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskstatus.TaskStatusDTO;

import hexlet.code.service.TaskStatusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/task_statuses")
@AllArgsConstructor
public class TaskStatusesController {
    private final TaskStatusService taskStatusService;

    @GetMapping("")
    public ResponseEntity<List<TaskStatusDTO>> index() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(taskStatusService.getAll().size()))
                .body(taskStatusService.getAll());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatusDTO create(@Valid @RequestBody TaskStatusCreateDTO statusData) {
        return taskStatusService.create(statusData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        taskStatusService.delete(id);
    }
}
