package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Task;
import de.telran.transportcompanymanagementsystem.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") String id) {
        //http://localhost:8080/task/0ebb8160-7f5c-410d-8a00-13e6837b8a16
        return taskService.getTaskById(id);
    }

    @GetMapping("/waybillnumber/{waybillNumber}")
    public Task getTaskByWaybillNumber(@PathVariable("waybillNumber") String waybillNumber) {
        //http://localhost:8080/task/waybillnumber/001
        return taskService.getTaskByWaybillNumber(waybillNumber);
    }

    @GetMapping("/weightcargo/morethan/{weight}")
    public List<Task> getTaskByWeightCargoWhenMoreThan(@PathVariable("weight") Float weight) {
        //http://localhost:8080/task/weightcargo/morethan/12000
        return taskService.getTaskByWeightCargoWhenMoreThan(weight);
    }

}
