package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select e, count(m.serviceType) as stCount from Employee e join e.tasks t " +
        "join t.vehicle v join v.maintenances m " +
        "GROUP BY e.employeeId, e.firstName, e.lastName")
    List<Employee> findEmployeeWithOneOrMoreVehicleMaintenance();
}
