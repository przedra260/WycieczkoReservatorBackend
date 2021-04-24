package pl.us.tripsbooking.trips.enums;

public enum TransportForm {
    BUS(1), PLANE(2);

    private int code;

    TransportForm(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
