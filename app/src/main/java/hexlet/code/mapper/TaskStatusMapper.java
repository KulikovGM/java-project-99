package hexlet.code.mapper;


import hexlet.code.dto.taskstatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskstatus.TaskStatusDTO;
import hexlet.code.dto.taskstatus.TaskStatusUpdateDTO;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import org.mapstruct.*;

@Mapper(
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskStatusMapper {
    public abstract TaskStatus map(TaskStatusDTO dto);
    public abstract TaskStatus map(TaskStatusCreateDTO dto);
    public abstract TaskStatusDTO map(TaskStatus model);
    public abstract void update(TaskStatusUpdateDTO update, @MappingTarget TaskStatus destination);
}
