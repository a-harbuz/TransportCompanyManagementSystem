package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.TaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskForDriverDto;
import de.telran.transportcompanymanagementsystem.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable("id") String id) {
        //http://localhost:8080/task/9981f28e-540f-4cdd-ac5e-fa256484e91f
        return taskService.getTaskById(id);
    }

    @GetMapping("/fordriver/waybillnumber/{waybillNumber}")
    public TaskForDriverDto getTaskForDriverByWaybillNumber(@PathVariable("waybillNumber") String waybillNumber) {
        //http://localhost:8080/task/fordriver/waybillnumber/001
        return taskService.getTaskForDriverByWaybillNumberDto(waybillNumber);
    }

    @GetMapping("/waybillnumber/{waybillNumber}")
    public TaskDto getTaskByWaybillNumber(@PathVariable("waybillNumber") String waybillNumber) {
        //http://localhost:8080/task/waybillnumber/001
        return taskService.getTaskByWaybillNumber(waybillNumber);
    }

    @GetMapping("/weightcargo/morethan/{weight}")
    public List<TaskDto> getTaskByWeightCargoWhenMoreThan(@PathVariable("weight") Float weight) {
        //http://localhost:8080/task/weightcargo/morethan/10000
        return taskService.getTaskByWeightCargoWhenMoreThan(weight);
    }

    @GetMapping("/companyname-waybillcost/{companyName}/{waybillCost}")
    public List<TaskDto> getTasksByCompanyNameAndWaybillCostMoreThan(@PathVariable("companyName") String companyName,
                                                                  @PathVariable("waybillCost") BigDecimal waybillCost) {
        //http://localhost:8080/task/companyname-waybillcost/Boehm-Klein/9000
        return taskService.getTasksByCompanyNameAndWaybillCostMoreThan(companyName,waybillCost);
    }

    @PostMapping("/new")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        //http://localhost:8080/task/new
        return taskService.create(taskDto);
    }

    @PutMapping("/update")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        //http://localhost:8080/task/update
        return taskService.update(taskDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable("id") String id) {
        //http://localhost:8080/task/delete/
        taskService.deleteTaskById(id);
    }
}
