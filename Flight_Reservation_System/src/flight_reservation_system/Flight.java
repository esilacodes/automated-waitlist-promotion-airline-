package flight_reservation_system;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Flight {
    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final int totalSeats;
    private String status = "On Time";

    private PriorityQueue<Booking> bookings;
    private Queue<Passenger> waitlist;

    public Flight(String flightNumber, String origin, String destination, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.bookings = new PriorityQueue<>();
        this.waitlist = new LinkedList<>();
    }

    public boolean isFull() {
        return bookings.size() >= totalSeats;
    }

    public String addBooking(Passenger p) {
        // Check for duplicate in confirmed bookings
        for (Booking b : bookings) {
            if (b.getPassenger().getPassengerName().equalsIgnoreCase(p.getPassengerName())) {
                return p.getPassengerName() + " passenger already has a booking.";
            }
        }
        // Check for duplicate in waitlist
        for (Passenger wp : waitlist) {
            if (wp.getPassengerName().equalsIgnoreCase(p.getPassengerName())) {
                return p.getPassengerName() + " is already on waitlist.";
            }
        }

        if (!isFull()) {
            bookings.add(new Booking(p, System.currentTimeMillis()));
            return "Booking confirmed for " + p.getPassengerName();
        } else {
            waitlist.add(p);
            return "Flight full. " + p.getPassengerName() + " added to waitlist.";
        }
    }

    public void removeBooking(String passengerName) {
        boolean removed = false;

        // Try to remove from bookings
        Iterator<Booking> itBook = bookings.iterator();
        while (itBook.hasNext()) {
            if (itBook.next().getPassenger().getPassengerName().equalsIgnoreCase(passengerName)) {
                itBook.remove();
                removed = true;
                System.out.println("Removed " + passengerName + " from bookings.");
                
                // Promote first person from waitlist
                Passenger nextP = waitlist.poll();
                if (nextP != null) {
                    bookings.add(new Booking(nextP, System.currentTimeMillis()));
                    System.out.println(nextP.getPassengerName() + " moved from waitlist to bookings.");
                }
                break;
            }
        }

        // If not found in bookings, check waitlist
        if (!removed) {
            Iterator<Passenger> itWait = waitlist.iterator();
            while (itWait.hasNext()) {
                if (itWait.next().getPassengerName().equalsIgnoreCase(passengerName)) {
                    itWait.remove();
                    removed = true;
                    System.out.println("Removed " + passengerName + " from waitlist.");
                    break;
                }
            }
        }

        if (!removed) {
            System.out.println("Passenger not found: " + passengerName);
        }
    }

    public String updateStatus(String newStatus) {
        this.status = newStatus;
        if (status.equalsIgnoreCase("cancelled")) {
            bookings.clear();
            waitlist.clear();
            return "Flight " + flightNumber + " is cancelled. All lists cleared.";
        }
        return "Flight status updated to: " + status;
    }

    public void displayDetails() {
        System.out.println("\nFlight Info: " + flightNumber + " [" + origin + " -> " + destination + "]");
        System.out.println("Status: " + status + " | Seats: " + totalSeats);
        System.out.println("--- Confirmed Bookings ---");
        bookings.forEach(System.out::println);
        System.out.println("--- Waitlist ---");
        waitlist.forEach(System.out::println);
    }

    // Getters
    public String getFlightNumber() { return flightNumber; }
}