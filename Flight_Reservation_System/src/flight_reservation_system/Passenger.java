package flight_reservation_system;

/**
 * Represents a passenger in the system.
 */
public class Passenger {
    private String passengerName;

    public Passenger(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    @Override
    public String toString() {
        return "Passenger{" + "name='" + passengerName + '\'' + '}';
    }
}