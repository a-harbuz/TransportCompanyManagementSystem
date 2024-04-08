package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Task getTaskByWaybillNumber (String waybillNumber);
    List<Task> getTaskByWeightCargoGreaterThan (Float weight);
    @Query("select t from Task t join t.company c where c.companyName = :companyName and t.waybillCost > :waybillCost")
    List<Task> getTasksByCompanyNameAndWaybillCostMoreThan (@Param("companyName") String companyName,
                                                            @Param("waybillCost") BigDecimal waybillCost);
}
