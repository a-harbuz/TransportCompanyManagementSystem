package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Contract;
import de.telran.transportcompanymanagementsystem.exception.ContractNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.ContractRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public Contract getContractById(String id) {
        return contractRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ContractNotFoundException(ErrorMessage.CONTRACT_NOT_FOUND));
    }

    @Override
    public Contract getContractByContractNumber(String contractNumber) {
        return contractRepository.getContractsByContractNumber(contractNumber);
        //Or Return Exaption
    }
}
