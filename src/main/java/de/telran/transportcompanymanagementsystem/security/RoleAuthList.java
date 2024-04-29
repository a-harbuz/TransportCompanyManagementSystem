package de.telran.transportcompanymanagementsystem.security;

public class RoleAuthList {

    public static final String USER_ROLE = "User";
    public static final String DRIVER_ROLE = "Driver";

    public static final String[] USER_LIST = {
        "/vehicle/all",
        "/company/all"
    };

    public static final String[] MANAGER_LIST = {
        "/vehicle/all",
        "/company/all"
    };

    public static final String[] DRIVER_LIST = {
        "/vehicle/*",
        "/company/all"
    };

    public static final String[] OWNER_LIST = {
        "/vehicle/all",
        "/company/all"
    };

}
