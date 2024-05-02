package de.telran.transportcompanymanagementsystem.security;

public class RoleAuthList {

    public static final String DRIVER_ROLE = "DRIVER";
    public static final String MANAGER_ROLE = "MANAGER";
    public static final String OWNER_ROLE = "OWNER";
    public static final String USER_ROLE = "USER";
    public static final String DEVELOPER_ROLE = "DEVELOPER";

    public static final String[] USER_LIST = {
            "/login",
            "/token",
            "/swagger-ui/**"
    };

    public static final String[] DRIVER_LIST = {
            "/vehicle/*",
            "/maintenance/*",
            "/task/fordriver/waybillnumber/*",
            "/maintenance/with-vehicle-and-company",
            "/vehicle/carnumber/*"
    };

    public static final String[] MANAGER_LIST = {
            "/vehicle/**",
            "/maintenance/**",
            "/contract/**",
            "/company/**",
            "/task/**",
            "/employee/**",
            "/employeeinfo/**",
    };

    public static final String[] OWNER_LIST = {
            "/vehicle/delete",
            "/company/delete"
    };


    public static final String[] DEVELOPER_LIST = {
            "/**"
    };
}
