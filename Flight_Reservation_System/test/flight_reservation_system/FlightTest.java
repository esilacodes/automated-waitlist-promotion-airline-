package flight_reservation_system;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class FlightTest {
    private Flight testFlight;

    @BeforeEach
    void setUp() {
        testFlight = new Flight("FL777", "Istanbul", "London", 2);
    }

    @Test
    @DisplayName("Test successful booking")
    void testBooking() {
        String res = testFlight.addBooking(new Passenger("User1"));
        assertTrue(res.toLowerCase().contains("confirmed"));
    }

    @Test
    @DisplayName("Test waitlist promotion")
    void testWaitlist() {
        testFlight.addBooking(new Passenger("User1"));
        testFlight.addBooking(new Passenger("User2"));
        testFlight.addBooking(new Passenger("WaitlistUser"));
        
        testFlight.removeBooking("User1");
        
        // WaitlistUser should now be in bookings
        String res = testFlight.addBooking(new Passenger("WaitlistUser"));
        assertTrue(res.toLowerCase().contains("already has a booking"));
    }
}