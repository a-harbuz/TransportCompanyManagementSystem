package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.TaskForDriver;
import de.telran.transportcompanymanagementsystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "transportationDate", target = "transportationDate")
    TaskForDriver toDtoForDriver(Task task);
}
