package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.CreateUpdateTaskDto;
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
    public TaskDto create(CreateUpdateTaskDto createUpdateTaskDto) {
        Task task = taskMapper.toEntity(createUpdateTaskDto);
        if (createUpdateTaskDto.getWaybillNumber() == null) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_CAN_NOT_BE_EMPTY);
        } else if (createUpdateTaskDto.getWaybillNumber().isEmpty()) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_CAN_NOT_BE_EMPTY);
        }
        Task taskByWaybillNumber = taskRepository.getTaskByWaybillNumber(createUpdateTaskDto.getWaybillNumber());
        if (taskByWaybillNumber != null) {
            throw new DataNotValidException(ErrorMessage.TASK_WAYBILL_NUMBER_EXIST);
        }
        Contract contract = contractRepository.findById(UUID.fromString(createUpdateTaskDto.getContract_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Company company = companyRepository.findById(UUID.fromString(createUpdateTaskDto.getCompany_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Vehicle vehicle = vehicleRepository.findById(UUID.fromString(createUpdateTaskDto.getVehicle_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        Employee employee = employeeRepository.findById(UUID.fromString(createUpdateTaskDto.getEmployee_id()))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
        task.setContract(contract);
        task.setCompany(company);
        task.setVehicle(vehicle);
        task.setEmployee(employee);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDto update(CreateUpdateTaskDto createUpdateTaskDto) {
        if (createUpdateTaskDto.getTaskId()==null)
            throw new TaskNotFoundException(ErrorMessage.TASK_ID_IS_ABSENT);
        Optional<Task> taskOptional = taskRepository.findById(createUpdateTaskDto.getTaskId());
        if (taskOptional.isEmpty()) throw new TaskNotFoundException(ErrorMessage.TASK_NOT_FOUND);
        Task task = taskOptional.get();
        if (createUpdateTaskDto.getTransportationDate()!=null)
            task.setTransportationDate(createUpdateTaskDto.getTransportationDate());
        if (createUpdateTaskDto.getAddressFrom()!=null)
            task.setAddressFrom(createUpdateTaskDto.getAddressFrom());
        if (createUpdateTaskDto.getAddressTo()!=null)
            task.setAddressTo(createUpdateTaskDto.getAddressTo());
        if (createUpdateTaskDto.getWeightCargo()!=null)
            task.setWeightCargo(createUpdateTaskDto.getWeightCargo());
        if (createUpdateTaskDto.getWaybillCost()!=null)
            task.setWaybillCost(createUpdateTaskDto.getWaybillCost());
        if (createUpdateTaskDto.getDistanceTraveledKilometers()!=null)
            task.setDistanceTraveledKilometers(createUpdateTaskDto.getDistanceTraveledKilometers());
        if (createUpdateTaskDto.getFuelCostsTraveled()!=null)
            task.setFuelCostsTraveled(createUpdateTaskDto.getFuelCostsTraveled());
        if (createUpdateTaskDto.getTaskStatus()!=null)
            task.setTaskStatus(createUpdateTaskDto.getTaskStatus());
        if (createUpdateTaskDto.getCommentIfTaskCanceled()!=null)
            task.setCommentIfTaskCanceled(createUpdateTaskDto.getCommentIfTaskCanceled());
        //Checking the presence of the contract_id
        if (createUpdateTaskDto.getContract_id()!=null)
            task.setContract(contractRepository.findById(UUID.fromString(createUpdateTaskDto.getContract_id()))
                            .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the company_id
        if (createUpdateTaskDto.getCompany_id()!=null)
            task.setCompany(companyRepository.findById(UUID.fromString(createUpdateTaskDto.getCompany_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the vehicle_id
        if (createUpdateTaskDto.getVehicle_id()!=null)
            task.setVehicle(vehicleRepository.findById(UUID.fromString(createUpdateTaskDto.getVehicle_id()))
                    .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
        //Checking the presence of the employee
        if (createUpdateTaskDto.getEmployee_id()!=null)
            task.setEmployee(employeeRepository.findById(UUID.fromString(createUpdateTaskDto.getEmployee_id()))
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
