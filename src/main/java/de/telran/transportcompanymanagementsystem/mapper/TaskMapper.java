package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.TaskForDriver;
import de.telran.transportcompanymanagementsystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface TaskMapper {
    @Mapping(source = "transportationDate", target = "transportationDate")
    TaskForDriver toDtoForDriver(Task task);
}
