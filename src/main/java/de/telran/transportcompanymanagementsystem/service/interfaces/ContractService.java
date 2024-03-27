package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Contract;

import java.util.List;

public interface ContractService {
    Contract getContractById (String id);
    Contract getContractByContractNumber (String contractNumber);
}
