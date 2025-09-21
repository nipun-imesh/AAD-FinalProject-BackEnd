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
    private Long id;

    private String useremail;
    private String userfirstname;
    private String userlastname;
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
    private double price;         // Ticket price
    private String bookingDate;   // Booking date
    private String passportNumber;
    private int passportExpiryDate;

    @ManyToOne
    @JoinColumn(name = "ticket_data_id")
    private TicketData ticketData;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
