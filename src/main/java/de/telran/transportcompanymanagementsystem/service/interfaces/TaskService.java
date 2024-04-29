package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.CreateTaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskForDriverDto;

import java.math.BigDecimal;
import java.util.List;

public interface TaskService {
    TaskDto getTaskById(String id);
    List<TaskDto> getAllTasks();
    TaskDto getTaskByWaybillNumber (String waybillNumber);
    List<TaskDto> getTaskByWeightCargoWhenMoreThan (Float weight);
    List<TaskDto> getTasksByCompanyNameAndWaybillCostMoreThan(String companyName, BigDecimal waybillCost);
    TaskForDriverDto getTaskForDriverByWaybillNumberDto (String waybillNumber);
    TaskDto create(CreateTaskDto createTaskDto);
    TaskDto update(CreateTaskDto createTaskDto);
    void deleteTaskById(String id);
}
