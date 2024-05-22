package de.telran.transportcompanymanagementsystem.security;

public class RoleAuthList {

    public static final String DRIVER_ROLE = "DRIVER";
    public static final String MANAGER_ROLE = "MANAGER";
    public static final String OWNER_ROLE = "OWNER";
    public static final String DEVELOPER_ROLE = "DEVELOPER";

    public static final String[] USER_LIST = {
            "/auth/**",
            "/login",
            "/swagger-ui.html",
            "/swagger-ui/**",

    };

    public static final String[] DRIVER_LIST = {
            "/maintenance/*",
            "/maintenance/cost/morethan/**",
            "/maintenance/with-vehicle-and-company",
            "/vehicle/*",
            "/vehicle/carnumber/**",
            "/vehicle/maintenancecost/**",
            "/task/fordriver/waybillnumber/*"
    };

    public static final String[] MANAGER_LIST = {
            "/company/*",
            "/company/name/*",
            "/company/update/name/**",
            "/contract/*",
            "/contract/number/*",
            "/employee/*",
            "/employee/with-vehicle-maintenance/*",
            "/employeeinfo/*",
            "/task/*",
            "/task/companyname-waybillcost/**",
            "/task/waybillnumber/*",
            "/task/weightcargo/morethan/*"

    };

    public static final String[] OWNER_LIST = {
            "/company/delete/*",
            "/task/delete/*",
            "/vehicle/delete/*",
            "/role/*"
    };

    public static final String[] DEVELOPER_LIST = {
            "/actuator/**"
    };
}
