package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CreateUpdateTaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskForDriverDto;
import de.telran.transportcompanymanagementsystem.entity.Task;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskDto toDto(Task task);
    @Mappings({
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),
            @Mapping(target = "weightCargo", expression = "java(Float.valueOf(createUpdateTaskDto.getWeightCargo()))"),
    })
    Task toEntity(CreateUpdateTaskDto createUpdateTaskDto);
    List<TaskDto> toDtoList(List<Task> tasks);
    @Mapping(source = "transportationDate", target = "transportationDate")
    TaskForDriverDto toDtoForDriver(Task task);
}
