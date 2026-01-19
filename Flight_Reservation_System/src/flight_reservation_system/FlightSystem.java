package flight_reservation_system;

import java.util.HashMap;

public class FlightSystem {
    private HashMap<String, Flight> flights;

    public FlightSystem() {
        this.flights = new HashMap<>();
    }

    public String addFlight(String num, String origin, String destination, int seats) {
        if (flights.containsKey(num)) {
            return "Error: Flight " + num + " already exists.";
        }
        flights.put(num, new Flight(num, origin, destination, seats));
        return "Flight " + num + " added successfully.";
    }

    public String bookTicket(String name, String flightNum) {
        Flight f = flights.get(flightNum);
        return (f != null) ? f.addBooking(new Passenger(name)) : "Error: Flight not found.";
    }

    public void cancelBooking(String name, String flightNum) {
        Flight f = flights.get(flightNum);
        if (f != null) f.removeBooking(name);
        else System.out.println("Error: Flight not found.");
    }

    public String updateFlightStatus(String flightNum, String status) {
        Flight f = flights.get(flightNum);
        return (f != null) ? f.updateStatus(status) : "Error: Flight not found.";
    }

    public void viewFlightDetails(String flightNum) {
        Flight f = flights.get(flightNum);
        if (f != null) f.displayDetails();
        else System.out.println("Error: Flight not found.");
    }
}