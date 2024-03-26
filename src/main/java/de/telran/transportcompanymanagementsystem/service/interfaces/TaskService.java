package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Task;

import java.util.List;

public interface TaskService {
    Task getTaskById(String id);
    Task getTaskByWaybillNumber (String waybillNumber);
    List<Task> getTaskByWeightCargoWhenMoreThan (Float weight);
}
