package de.telran.transportcompanymanagementsystem.security;

public class RoleAuthList {

    public static final String DRIVER_ROLE = "DRIVER";
    public static final String MANAGER_ROLE = "MANAGER";
    public static final String OWNER_ROLE = "OWNER";
    public static final String USER_ROLE = "USER";
    public static final String DEVELOPER_ROLE = "DEVELOPER";

    public static final String[] DRIVER_LIST = {
            "/vehicle/*",
            "/maintenance/*",
            "/task/fordriver/waybillnumber/*",
            "/maintenance/with-vehicle-and-company"
    };

    public static final String[] MANAGER_LIST = {
            "/vehicle/**",
            "/maintenance/**",
            "contract/**",
            "/task/**",
            "/company/**",
            "/employee/**"
    };

    public static final String[] OWNER_LIST = {
            "/vehicle/**",
            "/maintenance/**",
            "contract/**",
            "/task/**",
            "/company/**"
    };

    public static final String[] USER_LIST = {
            "/vehicle/all",
            "/company/all"
    };

    public static final String[] DEVELOPER_LIST = {
            "/**"
    };

}
