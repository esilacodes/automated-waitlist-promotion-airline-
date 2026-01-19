package flight_reservation_system;

import java.util.Scanner;

/**
 * Main entry point for the Flight Reservation System.
 * Handles the Command Line Interface (CLI) and user interactions.
 */
public class SystemFlightReservation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FlightSystem system = new FlightSystem();

        // Initial welcome message and list of available commands
        System.out.println("=== Flight Reservation System ===");
        System.out.println("Available Commands:");
        System.out.println(" add_flight <FlightNum> <Origin> <Destination> <Seats>");
        System.out.println(" book_ticket <PassengerName> <FlightNum>");
        System.out.println(" cancel_booking <PassengerName> <FlightNum>");
        System.out.println(" update_flight_status <FlightNum> <NewStatus>");
        System.out.println(" view_flight_details <FlightNum>");
        System.out.println(" exit");

        while (true) {
            System.out.print("\n> ");
            String input = sc.nextLine().trim();

            // Exit the application
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("System shutting down...");
                break; 
            }

            // Split the input into parts to identify command and arguments
            String[] parts = input.split(" ");
            if (parts.length == 0 || parts[0].isEmpty()) {
                continue;
            }

            String command = parts[0].toLowerCase();

            switch (command) {
                // Command to create and add a new flight to the system
                case "add_flight":
                    if (parts.length < 5) {
                        System.out.println("Usage: add_flight <FlightNum> <Origin> <Destination> <Seats>");
                        break;
                    }
                    try {
                        String num = parts[1];
                        String origin = parts[2];
                        String dest = parts[3];
                        int seats = Integer.parseInt(parts[4].trim());
                        System.out.println(system.addFlight(num, origin, dest, seats));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Seats must be a valid number.");
                    }
                    break;

                // Command to book a ticket for a passenger
                case "book_ticket":
                    if (parts.length < 3) {
                        System.out.println("Usage: book_ticket <PassengerName> <FlightNum>");
                        break;
                    }
                    System.out.println(system.bookTicket(parts[1], parts[2]));
                    break;

                // Command to remove a passenger's booking or waitlist entry
                case "cancel_booking":
                    if (parts.length < 3) {
                        System.out.println("Usage: cancel_booking <PassengerName> <FlightNum>");
                        break;
                    }
                    system.cancelBooking(parts[1], parts[2]);
                    break;

                // Command to update flight status (e.g., Delayed, Cancelled, On Time)
                case "update_flight_status":
                    if (parts.length < 3) {
                        System.out.println("Usage: update_flight_status <FlightNum> <NewStatus>");
                        break;
                    }
                    System.out.println(system.updateFlightStatus(parts[1], parts[2]));
                    break;

                // Command to display all information about a specific flight
                case "view_flight_details":
                    if (parts.length < 2) {
                        System.out.println("Usage: view_flight_details <FlightNum>");
                        break;
                    }
                    system.viewFlightDetails(parts[1]);
                    break;

                // Handle unrecognized commands
                default:
                    System.out.println("Unknown command. Please follow the instructions above.");
                    break;
            }
        }
        sc.close(); // Close the scanner resource
    }
}