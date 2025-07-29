package lk.ijse.gdse.wanderlust.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketBookingDTO {
    private Integer id;
    private String useremail;
    private String username;
    private String useraddress;
    private String userphone;
    private String fromLocation;   // Match with entity field name
    private String toLocation;     // Match with entity field name
    private String date;           // Flight date
    private String time;           // Flight time
    private String flightNumber;   // Flight no.
    private String seatNumber;     // Seat no.
    private String classType;      // Economy, Business, etc.
    private int passengerCount;    // Number of passengers
    private double price;          // Ticket price
    private String paymentStatus;  // Paid, Pending, etc.
    private String bookingDate;    // Booking date
}
