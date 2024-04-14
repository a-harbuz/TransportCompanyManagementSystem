package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, UUID> {
    EmployeeInfo findByLogin (String login);
}
