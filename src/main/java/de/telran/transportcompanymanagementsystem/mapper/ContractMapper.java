package de.telran.transportcompanymanagementsystem.mapper;

import org.mapstruct.Mapper;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface ContractMapper {
}
