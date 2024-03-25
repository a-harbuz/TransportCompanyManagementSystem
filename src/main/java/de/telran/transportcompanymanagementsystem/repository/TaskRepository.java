package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Task getTaskByWaybillNumber (String waybillNumber);
    List<Task> getTaskByWeightCargoGreaterThan (Float weight);
}
