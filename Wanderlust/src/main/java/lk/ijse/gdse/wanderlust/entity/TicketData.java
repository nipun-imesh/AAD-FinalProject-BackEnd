package lk.ijse.gdse.wanderlust.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket_preview")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Flight details
    private String airlineName;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTerminal;
    private String arrivalTerminal;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String flightDuration;

    // Fare & price
    private Double totalPrice;
    private String currency;
    private Boolean refundable;

    // Baggage & services
    private Double cabinBaggageKg;
    private Double checkedBaggageKg;
    private Boolean mealIncluded;
    private Boolean seatSelectionAvailable;

    // Class & conditions
    private String travelClass;
    private String ticketType;
    @Column(columnDefinition = "TEXT")
    private String changePolicy;
    @Column(columnDefinition = "TEXT")
    private String cancellationPolicy;


}
