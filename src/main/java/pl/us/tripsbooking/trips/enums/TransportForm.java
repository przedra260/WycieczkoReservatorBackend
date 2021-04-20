package pl.us.tripsbooking.trips.enums;

public enum TransportForm {
    BUS(1),
    PLANE(2);

    private final int transportId;

    TransportForm(int transportId) {
        this.transportId = transportId;
    }

    public int getTransportId() {
        return transportId;
    }
}
