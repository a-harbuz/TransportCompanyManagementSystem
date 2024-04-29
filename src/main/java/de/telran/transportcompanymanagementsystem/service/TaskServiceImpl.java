package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.CreateTaskDto;
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
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (!tasks.isEmpty()) {
            return taskMapper.toDtoList(tasks);
        } else {
            throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        }
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
    public TaskDto create(CreateTaskDto createTaskDto) {
        Task task = taskMapper.toEntity(createTaskDto);
        if (createTaskDto.getWaybillNumber() == null) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_CAN_NOT_BE_EMPTY);
        } else if (createTaskDto.getWaybillNumber().isEmpty()) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_CAN_NOT_BE_EMPTY);
        }
        Task taskByWaybillNumber = taskRepository.getTaskByWaybillNumber(createTaskDto.getWaybillNumber());
        if (taskByWaybillNumber != null) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_NUMBER_EXIST);
        }
        Contract contract = contractRepository.findById(UUID.fromString(createTaskDto.getContract_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Company company = companyRepository.findById(UUID.fromString(createTaskDto.getCompany_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Vehicle vehicle = vehicleRepository.findById(UUID.fromString(createTaskDto.getVehicle_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Employee employee = employeeRepository.findById(UUID.fromString(createTaskDto.getEmployee_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        task.setContract(contract);
        task.setCompany(company);
        task.setVehicle(vehicle);
        task.setEmployee(employee);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDto update(CreateTaskDto createTaskDto) {
        if (createTaskDto.getTaskId()==null)
            throw new TaskNotFoundException(ErrorMessage.TASK_ID_IS_ABSENT);
        Optional<Task> taskOptional = taskRepository.findById(createTaskDto.getTaskId());
        if (taskOptional.isEmpty()) throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        Task task = taskOptional.get();
        if (createTaskDto.getTransportationDate()!=null)
            task.setTransportationDate(createTaskDto.getTransportationDate());
        if (createTaskDto.getAddressFrom()!=null)
            task.setAddressFrom(createTaskDto.getAddressFrom());
        if (createTaskDto.getAddressTo()!=null)
            task.setAddressTo(createTaskDto.getAddressTo());
        if (createTaskDto.getWeightCargo()!=null)
            task.setWeightCargo(createTaskDto.getWeightCargo());
        if (createTaskDto.getWaybillCost()!=null)
            task.setWaybillCost(createTaskDto.getWaybillCost());
        if (createTaskDto.getDistanceTraveledKilometers()!=null)
            task.setDistanceTraveledKilometers(createTaskDto.getDistanceTraveledKilometers());
        if (createTaskDto.getFuelCostsTraveled()!=null)
            task.setFuelCostsTraveled(createTaskDto.getFuelCostsTraveled());
        if (createTaskDto.getTaskStatus()!=null)
            task.setTaskStatus(createTaskDto.getTaskStatus());
        if (createTaskDto.getCommentIfTaskCanceled()!=null)
            task.setCommentIfTaskCanceled(createTaskDto.getCommentIfTaskCanceled());
        //Checking the presence of the contract_id
        if (createTaskDto.getContract_id()!=null)
            task.setContract(contractRepository.findById(UUID.fromString(createTaskDto.getContract_id()))
                            .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the company_id
        if (createTaskDto.getCompany_id()!=null)
            task.setCompany(companyRepository.findById(UUID.fromString(createTaskDto.getCompany_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the vehicle_id
        if (createTaskDto.getVehicle_id()!=null)
            task.setVehicle(vehicleRepository.findById(UUID.fromString(createTaskDto.getVehicle_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the employee
        if (createTaskDto.getEmployee_id()!=null)
            task.setEmployee(employeeRepository.findById(UUID.fromString(createTaskDto.getEmployee_id()))
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
