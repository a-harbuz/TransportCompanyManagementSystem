package util;

import de.telran.transportcompanymanagementsystem.entity.*;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class EntityCreator {
    public static Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(UUID.fromString("26e41ad9-0482-4808-9dbb-c917631f1b56"));
        vehicle.setVehicleType(VehicleType.TRUCK20T);
        vehicle.setName("MAN");
        vehicle.setModel("TGS 35.480");
        vehicle.setYearManufacture("2008");
        vehicle.setCarNumber("AE2387KM");
        vehicle.setInitialKilometers(52300);
        vehicle.setPrice(50000);
        vehicle.setVehicleStatus(VehicleStatus.WORKING);
        return vehicle;
    }

    public static Vehicle getNewVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(VehicleType.TRUCK20T);
        vehicle.setName("MAN");
        vehicle.setModel("TGS 35.480");
        vehicle.setYearManufacture("2010");
        vehicle.setCarNumber("TEST_CAR_NUMBER");
        vehicle.setInitialKilometers(53000);
        vehicle.setPrice(20000);
        vehicle.setVehicleStatus(VehicleStatus.WORKING);
        vehicle.setCreatedAt(Timestamp.valueOf("2024-03-31 09:00:00.000000000"));
        return vehicle;
    }

    public static Company getCompany() {
        Company company = new Company();
        company.setCompanyId(UUID.fromString("0a8de57b-4ac3-43f9-9ab4-77784de2554a"));
        company.setCompanyName("Boehm-Klein");
        company.setContactFirstName("Christel");
        company.setContactLastName("Waters");
        company.setEmail("wolff@gmail.com");
        company.setAddress("Suite 366 87303 Magnolia Vista, Port Jaimieland, WA 19021");
        company.setPhone("015-888-4863");
        return company;
    }
    public static Company getCompany2() {
        Company company = new Company();
        company.setCompanyId(UUID.fromString("875eeb54-d18a-4cfc-8efc-d984c8c362ff"));
        company.setCompanyName("Haley-Stoltenberg");
        company.setContactFirstName("Carlo");
        company.setContactLastName("Mills");
        company.setEmail("homenick@gmail.com");
        company.setAddress("Apt. 644 83289 Frances Canyon, Purdyside, AK 93792");
        company.setPhone("0151-501-994");
        return company;
    }
    public static Company getNewCompany() {
        Company company = new Company();
        company.setCompanyId(UUID.fromString("a4e95cd9-6717-4c59-aa42-431d4e2bffb1"));
        company.setCompanyName("Wisa and Sons");
        company.setContactFirstName("Santiago");
        company.setContactLastName("Ratke");
        company.setEmail("santiago@gmail.com");
        company.setAddress("Suite 142 9185 Onie Grove, Kirlinstad, ND 85329");
        company.setPhone("0154-765-2407");
        company.setCreatedAt(Timestamp.valueOf("2024-03-31 09:00:00.000000000"));
        return company;
    }

    public static Maintenance getMaintenance() {
        Maintenance maintenance = new Maintenance();
        maintenance.setMId(UUID.fromString("1d8c0c4d-e54f-4ea7-b6ba-3640ddc2aa06"));
        maintenance.setServiceType(ServiceType.REPAIR_CHASSIS);
        maintenance.setMaintenanceCost(BigDecimal.valueOf(654.24));
        maintenance.setMaintenanceKilometers(71400);
        maintenance.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return maintenance;
    }

    public static Contract getContract() {
        Contract contract = new Contract();
        contract.setContractId(UUID.fromString("c8e0d900-fcd7-4182-925c-fb3a8d010243"));
        contract.setContractNumber("001");
        contract.setContractName("договор перевозки грузов");
        contract.setCostTransportationUnderContract(BigDecimal.valueOf(20000.00));
        contract.setTotalCostTransportedGoods(BigDecimal.valueOf(50000.00));
        contract.setContractStatus(ContractStatus.ACTIVE);
        return contract;
    }

    public static EmployeeInfo getEmployeeInfo() {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setEmployeeInfoId(UUID.fromString("18907be0-b307-49d6-8f4c-27f9c26589d4"));
        employeeInfo.setAddress("319 Cordell Square, Kazukoside, DE 54119-3469");
        employeeInfo.setPhone("0171-619-9624");
        employeeInfo.setDrivingLicenseCategory(DrivingLicenseCategory.C);
        employeeInfo.setLogin("blanca.cummings");
        employeeInfo.setPassword("22b684ec92deee34712b1056b280b36f2fdab220cc6");
        return employeeInfo;
    }

    public static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(UUID.fromString("4534a894-9a50-463b-bc06-5840689efe9a"));
        employee.setFirstName("Rickey");
        employee.setLastName("Schowalter");
        employee.setDriver(true);
        employee.setWorking(true);
        return employee;
    }

    public static Task getTask() {
        Task task = new Task();
        task.setTaskId(UUID.fromString("0ebb8160-7f5c-410d-8a00-13e6837b8a16"));
        task.setTransportationDate(LocalDateTime.parse("2024-03-12T00:00:00"));
        task.setAddressFrom("Suite 029 4039 Pfeffer Keys, West Wilfordfurt, NE 11123");
        task.setAddressTo("Apt. 728 1039 Fritsch Garden, Alfredmouth, AR 04647");
        task.setWeightCargo(4000);
        task.setWaybillNumber("004");
        task.setWaybillCost(BigDecimal.valueOf(2000.00));
        task.setDistanceTraveledKilometers(250);
        task.setFuelCostsTraveled(BigDecimal.valueOf(120));
        task.setTaskStatus(TaskStatus.CANCELLED);
        task.setCommentIfTaskCanceled("The car broke down on the way.");
        return task;
    }

    public static Role getRole(){
        Role role = new Role();
        role.setRId(UUID.fromString("4faba779-9226-4e9c-a28b-f6a534e95365"));
        role.setRoleName("Driver");
        return role;
    }

    public static Authority getAuthority(){
        Authority role = new Authority();
        role.setAId(UUID.fromString("4fb1e8f0-41d2-4872-a3d1-3aa6818ac266"));
        role.setAuthorityName("DriverAuthority");
        return null;
    }

}
