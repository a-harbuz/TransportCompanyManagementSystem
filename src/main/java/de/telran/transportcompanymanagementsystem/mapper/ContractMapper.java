package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.ContractDto;
import de.telran.transportcompanymanagementsystem.entity.Contract;
import org.mapstruct.Mapper;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", imports = Timestamp.class) //imports = {Timestamp.class, ContractStatus.class}
public interface ContractMapper {
    ContractDto toDto(Contract contract);
}
