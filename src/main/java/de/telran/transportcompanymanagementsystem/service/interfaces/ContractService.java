package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.ContractDto;


public interface ContractService {
    ContractDto getContractById (String id);
    ContractDto getContractByContractNumber (String contractNumber);
}
