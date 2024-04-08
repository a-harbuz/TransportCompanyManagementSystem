package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Task;

import java.math.BigDecimal;
import java.util.List;

public interface TaskService {
    Task getTaskById(String id);
    Task getTaskByWaybillNumber (String waybillNumber);
    List<Task> getTaskByWeightCargoWhenMoreThan (Float weight);
    List<Task> getTasksByCompanyNameAndWaybillCostMoreThan(String companyName, BigDecimal waybillCost);
}
