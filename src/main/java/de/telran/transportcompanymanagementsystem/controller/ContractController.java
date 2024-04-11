package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.ContractDto;
import de.telran.transportcompanymanagementsystem.service.interfaces.ContractService;
import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/{id}")
    public ContractDto getContractById(@PathVariable("id") @UuidChecker String id) {
        //http://localhost:8080/contract/c8e0d900-fcd7-4182-925c-fb3a8d010243
        return contractService.getContractById(id);
    }

    @GetMapping("/number/{contractNumber}")
    public ContractDto getContractByContractNumber(@PathVariable("contractNumber") String contractNumber) {
        //http://localhost:8080/contract/number/001
        return contractService.getContractByContractNumber(contractNumber);
    }

}
