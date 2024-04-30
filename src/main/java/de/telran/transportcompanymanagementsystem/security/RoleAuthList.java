package de.telran.transportcompanymanagementsystem.security;

public class RoleAuthList {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_DRIVER = "ROLE_DRIVER";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_OWNER = "ROLE_OWNER";

    public static final String[] USER_LIST = {
        "/vehicle/all",
        "/company/all"
    };

    public static final String[] DRIVER_LIST = {
            "/vehicle/*",
            "/company/all"
    };

    public static final String[] MANAGER_LIST = {
        "/vehicle/all",
        "/company/all"
    };

    public static final String[] OWNER_LIST = {
        "/vehicle/all",
        "/company/all"
    };

}
