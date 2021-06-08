package pl.us.tripsbooking.users.enums;

public enum RoleEnum {
    ROLE_ADMIN, ROLE_USER, ROLE_TRIPS_GUIDE;

    public static RoleEnum getByName(String name) {
        for (RoleEnum role : RoleEnum.values())
            if (role.name().equalsIgnoreCase(name))
                return role;
        return null;
    }
}
