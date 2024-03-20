package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployeeName() {
        return null;
    }

    @Override
    public String getEmployeeById(String id) {
        //Сервисы в Spring содержат бизнес-логику приложения.
        //Они обычно выполняют операции, такие как извлечение данных из базы данных, обработка данных и
        //передача их контроллерам для отображения или других действий.(здесь движуха!)

        //Репозитории представляют слой доступа к данным и обеспечивают взаимодействие с базой данных.
        //Репозитории обычно внедряются в сервисы с использованием механизмов внедрения зависимостей Spring.
        //return (id.equals("1c9859bd-8d9b-49e0-88d7-58f8f3c1c4b3")) ? "Есть такой Работник" : "User(id): " + id;

        return (employeeRepository.findById(UUID.fromString(id)).isPresent()) ?
                employeeRepository.findById(UUID.fromString(id)).get().getFirstName() //"Есть такой Работник"
                : "Нету такого";
    }
}
