package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Task;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
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
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
    }

    @Override
    public Task getTaskByWaybillNumber(String waybillNumber) {
        return taskRepository.getTaskByWaybillNumber(waybillNumber);
    }

    @Override
    public List<Task> getTaskByWeightCargoWhenMoreThan(Float weight) {
        //return taskRepository.getTaskByWeightCargoGreaterThan(weight);
        return taskRepository.getTaskByWeightCargoGreaterThan(weight);
    }
}
