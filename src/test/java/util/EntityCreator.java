package util;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;

import java.util.UUID;

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
    public static Vehicle getVehicle2() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(UUID.fromString("9a04a59b-940f-4e2c-be40-5d2366b3e7b1"));
        vehicle.setVehicleType(VehicleType.TRUCK10T);
        vehicle.setName("VOLVO");
        vehicle.setModel("XC90");
        vehicle.setYearManufacture("2018");
        vehicle.setCarNumber("K234BA");
        vehicle.setInitialKilometers(120000);
        vehicle.setPrice(40000);
        vehicle.setVehicleStatus(VehicleStatus.WORKING);
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


}
