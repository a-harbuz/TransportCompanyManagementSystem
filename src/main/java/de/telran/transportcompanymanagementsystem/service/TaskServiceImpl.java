package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.TaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskForDriverDto;
import de.telran.transportcompanymanagementsystem.entity.Task;
import de.telran.transportcompanymanagementsystem.exception.TaskNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.TaskMapper;
import de.telran.transportcompanymanagementsystem.repository.TaskRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public TaskDto getTaskById(String id) {
        return taskMapper.toDto(taskRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND)));
    }

    @Override
    public TaskDto getTaskByWaybillNumber(String waybillNumber) {
        Task task = taskRepository.getTaskByWaybillNumber(waybillNumber);
        if (task != null) {
            return taskMapper.toDto(task);
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
    }

    @Override
    public List<TaskDto> getTaskByWeightCargoWhenMoreThan(Float weight) {
        List<Task> tasks = taskRepository.getTaskByWeightCargoGreaterThan(weight);
        if (!tasks.isEmpty()) {
            return taskMapper.toDtoList(tasks);
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
    }

    @Override
    public List<TaskDto> getTasksByCompanyNameAndWaybillCostMoreThan(String companyName, BigDecimal waybillCost) {
        List<Task> tasks = taskRepository.getTasksByCompanyNameAndWaybillCostMoreThan(companyName, waybillCost);
        if (!tasks.isEmpty()) {
            return taskMapper.toDtoList(tasks);
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
    }

    @Override
    public TaskForDriverDto getTaskForDriverByWaybillNumberDto(String waybillNumber) {
        Task task = taskRepository.getTaskByWaybillNumber(waybillNumber);
        if (task != null) {
            return taskMapper.toDtoForDriver(task);
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
    }
}
