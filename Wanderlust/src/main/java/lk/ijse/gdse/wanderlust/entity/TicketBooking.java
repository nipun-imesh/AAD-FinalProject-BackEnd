package lk.ijse.gdse.wanderlust.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TicketBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String useremail;
    private String username;
    private String useraddress;
    private String userphone;

    @Column(name = "from_location")
    private String fromLocation; // Changed from "from"

    @Column(name = "to_location")
    private String toLocation;   // Changed from "to"

    private String date;          // Flight date
    private String time;          // Flight time
    private String flightNumber;  // Flight no.
    private String seatNumber;    // Seat no.
    private String classType;     // Economy, Business, etc.
    private int passengerCount;   // Number of passengers
    private double price;         // Ticket price
    private String paymentStatus; // Paid, Pending, etc.
    private String bookingDate;   // Booking date

    @ManyToOne
    @JoinColumn(name = "ticket_data_id")
    private TicketData ticketData;
}
