package de.telran.transportcompanymanagementsystem.exception.errormessage;

public class ErrorMessage {
    public static final String EMPLOYEE_NOT_FOUND = "Employee with this ID was not found.";
    public static final String EMPLOYEE_EXIST = "Employee with this FirstName and LastName is already present.";
    public static final String EMPLOYEE_WITH_LOGIN_EXIST = "This Login is already present. Choose a different login.";
    public static final String EMPLOYEE_NOT_VALID_NAME = "First and last name must be at least 3 characters long.";
    public static final String EMPLOYEE_NOT_VALID_LOGIN = "Login must be at least 5 characters";
    public static final String VEHICLE_NOT_FOUND = "Vehicle with this ID was not found.";
    public static final String VEHICLE_NOT_FOUND_BY_CAR_NUMBER = "Vehicle with this car number was not found.";
    public static final String COMPANY_ID_IS_ABSENT = "CompanyID is absent.";
    public static final String COMPANY_NOT_FOUND = "Company with this ID was not found.";
    public static final String COMPANY_NAME_NOT_FOUND = "Company with this name was not found.";
    public static final String COMPANY_NAME_CAN_NOT_BE_EMPTY = "Company name can not be empty.";
    public static final String COMPANY_NAME_ALREADY_PRESENT = "Company with this name is already present.";
    public static final String DATA_NOT_FOUND = "Data with this ID was not found.";
    public static final String CONTRACT_NOT_FOUND = "Contract with this ID was not found.";
    public static final String CONTRACT_NOT_FOUND_BY_NUMBER = "Contract with this number was not found.";
    public static final String TASK_NOT_FOUND = "Task with this argument was not found.";
}
