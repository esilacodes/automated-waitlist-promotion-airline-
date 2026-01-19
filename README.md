# ✈️ Automated Waitlist Promotion System (Airline)

A Java CLI application demonstrating automated passenger promotion from waitlist to booking using PriorityQueues and HashMaps.

## 🌟 Key Features
- **Smart Priority Booking:** Confirmed bookings are managed using a `PriorityQueue`, ensuring passengers are handled based on their booking timestamp.
- **Automated Waitlist System:** Implements a seamless transition logic where waitlisted passengers are automatically promoted to confirmed status when a seat becomes available.
- **High-Performance Search:** Utilizes `HashMap` for $O(1)$ time complexity when looking up flight details by flight number.
- **Dynamic Status Updates:** Full support for flight cancellations and delays. Cancelling a flight automatically clears manifests to maintain data integrity.
- **Robust Error Handling:** Integrated input validation to prevent system crashes from invalid user commands.



## 🛠 Tech Stack & Data Structures
- **Language:** Java SE
- **HashMap:** For rapid flight indexing and retrieval.
- **PriorityQueue:** To manage confirmed bookings with time-based priority.
- **LinkedList (Queue):** To maintain a fair First-In-First-Out (FIFO) waitlist.

## 📋 Command Guide
- `add_flight <Num> <Origin> <Dest> <Seats>` ---> Create a new flight instance.
- `book_ticket <Name> <FlightNum>` ---> Attempt to book a seat or join the waitlist.
- `cancel_booking <Name> <FlightNum>` ---> Remove a passenger & trigger auto-promotion.
- `update_flight_status <Num> <Status>` ---> Change status (e.g., Delayed, Cancelled).
- `view_flight_details <Num>` ---> Display manifests and current capacity.

## 👥 Contributors
This project was developed through a collaborative effort:

- **Esila Gül Kıvrak** - [GitHub Profile](https://github.com/esilacodes)
- **Esmanur Çulbasan** - [GitHub Profile](https://github.com/esmanur1219)

*We worked closely together to design and debug the automated waitlist promotion logic, ensuring a smooth transition for passengers when seats open up.*

## 📂 Project Structure
```text
src/systemflightreservation/
├── Passenger.java               # Passenger data model
├── Booking.java                 # Booking logic & Priority implementation
├── Flight.java                  # Core seat & Waitlist management
├── FlightSystem.java            # Flight inventory & System controller
└── SystemFlightReservation.java # Interactive CLI Entry Point
