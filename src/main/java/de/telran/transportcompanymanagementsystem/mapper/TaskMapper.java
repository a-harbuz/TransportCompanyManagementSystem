package de.telran.transportcompanymanagementsystem.mapper;

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
    @AfterMapping
    default void getTaskRelatedId(@MappingTarget TaskDto taskDto,
                                Task task){
        taskDto.setContract_id(task.getContract().getContractId().toString());
        taskDto.setCompany_id(task.getCompany().getCompanyId().toString());
        taskDto.setVehicle_id(task.getVehicle().getVehicleId().toString());
        taskDto.setEmployee_id(task.getEmployee().getEmployeeId().toString());
    }
    @Mappings({
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),
            @Mapping(target = "weightCargo", expression = "java(Float.valueOf(taskDto.getWeightCargo()))"),
    })
    Task toEntity(TaskDto taskDto);
    List<TaskDto> toDtoList(List<Task> tasks);
    @Mapping(source = "transportationDate", target = "transportationDate")
    TaskForDriverDto toDtoForDriver(Task task);
}
