package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.TaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskForDriverDto;
import de.telran.transportcompanymanagementsystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface TaskMapper {
    TaskDto toDto(Task task);
    List<TaskDto> toDtoList(List<Task> tasks);
    @Mapping(source = "transportationDate", target = "transportationDate")
    TaskForDriverDto toDtoForDriver(Task task);
}
