package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.ContractDto;
import de.telran.transportcompanymanagementsystem.entity.Contract;
import de.telran.transportcompanymanagementsystem.exception.ContractNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.ContractMapper;
import de.telran.transportcompanymanagementsystem.repository.ContractRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Override
    public ContractDto getContractById(String id) {
        Contract contract = contractRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ContractNotFoundException(ErrorMessage.CONTRACT_NOT_FOUND));
        System.out.println(contract);
        return contractMapper.toDto(contract);
    }

    @Override
    public ContractDto getContractByContractNumber(String contractNumber) {
        Contract contract = contractRepository.findContractByContractNumber(contractNumber);
        if (contract != null) {
            return contractMapper.toDto(contract);
        } else {
            throw new ContractNotFoundException(ErrorMessage.CONTRACT_NOT_FOUND_BY_NUMBER);
        }
    }
}
