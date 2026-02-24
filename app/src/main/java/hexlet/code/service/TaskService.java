//package hexlet.code.service;
//
//import hexlet.code.component.TaskSpecification;
//import hexlet.code.exception.ResourceNotFoundException;
//import hexlet.code.mapper.TaskMapper;
//import hexlet.code.model.dto.Task.TaskCreateDTO;
//import hexlet.code.model.dto.Task.TaskDTO;
//import hexlet.code.model.dto.Task.TaskUpdateDTO;
//import hexlet.code.model.dto.TaskParamsDTO;
//import hexlet.code.repository.TaskRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true, rollbackFor = Exception.class)
//@RequiredArgsConstructor
//public class TaskService {
//
//    private final TaskRepository taskRepository;
//    private final TaskSpecification taskSpecification;
//    private final TaskMapper mapper;
//
//    public List<TaskDTO> getAll(TaskParamsDTO params) {
//        var spec = taskSpecification.build(params);
//        var tasks = taskRepository.findAll(spec);
//        return tasks.stream().map(mapper::map).toList();
//    }
//
//    public TaskDTO findById(Long id) {
//        var task = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
//        return mapper.map(task);
//    }
//
//    public TaskDTO create(TaskCreateDTO taskData) {
//        var task = mapper.map(taskData);
//        taskRepository.save(task);
//        return mapper.map(task);
//    }
//
//    public TaskDTO update(TaskUpdateDTO taskData, Long id) {
//        var task = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
//        mapper.update(taskData, task);
//        taskRepository.save(task);
//        return mapper.map(task);
//    }
//
//    public void delete(Long id) {
//        if (!taskRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Task not found");
//        }
//        taskRepository.deleteById(id);
//    }
//}