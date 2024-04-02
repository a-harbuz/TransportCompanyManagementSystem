package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Task;
import de.telran.transportcompanymanagementsystem.exception.TaskNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.TaskRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task getTaskById(String id) {
        return taskRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND));
    }

    @Override
    public Task getTaskByWaybillNumber(String waybillNumber) {
        Task task = taskRepository.getTaskByWaybillNumber(waybillNumber);
        if (task != null) {
            return task;
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
    }

    @Override
    public List<Task> getTaskByWeightCargoWhenMoreThan(Float weight) {
        List<Task> tasks = taskRepository.getTaskByWeightCargoGreaterThan(weight);
        if (!tasks.isEmpty()) {
            return tasks;
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
    }
}
