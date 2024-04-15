package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.TaskDto;
import de.telran.transportcompanymanagementsystem.dto.TaskForDriverDto;
import de.telran.transportcompanymanagementsystem.entity.*;
import de.telran.transportcompanymanagementsystem.exception.*;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.TaskMapper;
import de.telran.transportcompanymanagementsystem.repository.*;
import de.telran.transportcompanymanagementsystem.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ContractRepository contractRepository;
    private final CompanyRepository companyRepository;
    private final VehicleRepository vehicleRepository;
    private final EmployeeRepository employeeRepository;
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

    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        if (taskDto.getWaybillNumber() == null) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_CAN_NOT_BE_EMPTY);
        } else if (taskDto.getWaybillNumber().isEmpty()) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_CAN_NOT_BE_EMPTY);
        }
        Task taskByWaybillNumber = taskRepository.getTaskByWaybillNumber(taskDto.getWaybillNumber());
        if (taskByWaybillNumber != null) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_NUMBER_EXIST);
        }
        Contract contract = contractRepository.findById(UUID.fromString(taskDto.getContract_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Company company = companyRepository.findById(UUID.fromString(taskDto.getCompany_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Vehicle vehicle = vehicleRepository.findById(UUID.fromString(taskDto.getVehicle_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Employee employee = employeeRepository.findById(UUID.fromString(taskDto.getEmployee_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        task.setContract(contract);
        task.setCompany(company);
        task.setVehicle(vehicle);
        task.setEmployee(employee);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        if (taskDto.getTaskId()==null)
            throw new TaskNotFoundException(ErrorMessage.TASK_ID_IS_ABSENT);
        Optional<Task> taskOptional = taskRepository.findById(taskDto.getTaskId());
        if (taskOptional.isEmpty()) throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        Task task = taskOptional.get();
        if (taskDto.getTransportationDate()!=null)
            task.setTransportationDate(taskDto.getTransportationDate());
        if (taskDto.getAddressFrom()!=null)
            task.setAddressFrom(taskDto.getAddressFrom());
        if (taskDto.getAddressTo()!=null)
            task.setAddressTo(taskDto.getAddressTo());
        if (taskDto.getWeightCargo()!=null)
            task.setWeightCargo(taskDto.getWeightCargo());
        if (taskDto.getWaybillCost()!=null)
            task.setWaybillCost(taskDto.getWaybillCost());
        if (taskDto.getDistanceTraveledKilometers()!=null)
            task.setDistanceTraveledKilometers(taskDto.getDistanceTraveledKilometers());
        if (taskDto.getFuelCostsTraveled()!=null)
            task.setFuelCostsTraveled(taskDto.getFuelCostsTraveled());
        if (taskDto.getTaskStatus()!=null)
            task.setTaskStatus(taskDto.getTaskStatus());
        if (taskDto.getCommentIfTaskCanceled()!=null)
            task.setCommentIfTaskCanceled(taskDto.getCommentIfTaskCanceled());
        //Checking the presence of the contract_id
        if (taskDto.getContract_id()!=null)
            task.setContract(contractRepository.findById(UUID.fromString(taskDto.getContract_id()))
                            .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the company_id
        if (taskDto.getCompany_id()!=null)
            task.setCompany(companyRepository.findById(UUID.fromString(taskDto.getCompany_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the vehicle_id
        if (taskDto.getVehicle_id()!=null)
            task.setVehicle(vehicleRepository.findById(UUID.fromString(taskDto.getVehicle_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the employee
        if (taskDto.getEmployee_id()!=null)
            task.setEmployee(employeeRepository.findById(UUID.fromString(taskDto.getEmployee_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        return taskMapper.toDto(taskRepository.saveAndFlush(task));
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND));
        taskRepository.deleteById(UUID.fromString(id));
    }
}
