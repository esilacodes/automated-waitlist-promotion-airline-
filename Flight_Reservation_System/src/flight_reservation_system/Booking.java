package flight_reservation_system;

/**
 * Represents a confirmed booking with priority based on booking time.
 */
public class Booking implements Comparable<Booking> {
    private Passenger passenger;
    private long bookingTime;

    public Booking(Passenger passenger, long bookingTime) {
        this.passenger = passenger;
        this.bookingTime = bookingTime;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public int compareTo(Booking other) {
        // Earlier booking time means higher priority
        return Long.compare(this.bookingTime, other.bookingTime);
    }

    @Override
    public String toString() {
        return "Booking{" + "passenger=" + passenger.getPassengerName() + 
               ", bookingTime=" + bookingTime + '}';
    }
}