package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
//public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    //Репозитории представляют слой доступа к данным и обеспечивают взаимодействие с базой данных.
    //Репозитории обычно внедряются в сервисы с использованием механизмов внедрения зависимостей Spring.
    //В Spring Data JPA репозитории могут быть созданы путем создания интерфейсов,
    //расширяющих org.springframework.data.jpa.repository.JpaRepository или другие интерфейсы репозиториев Spring Data.

    //Optional<Employee> findById(UUID id);
    Employee getReferenceById(UUID id);
}
