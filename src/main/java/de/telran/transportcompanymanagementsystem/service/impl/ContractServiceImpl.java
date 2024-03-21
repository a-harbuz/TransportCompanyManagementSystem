package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Contract;
import de.telran.transportcompanymanagementsystem.exception.errorMessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.ContractRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public Contract getContractById(String id) {
        try {
            return contractRepository.findById(UUID.fromString(id)).orElseThrow(()-> new UserPrincipalNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND));
        } catch (UserPrincipalNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
