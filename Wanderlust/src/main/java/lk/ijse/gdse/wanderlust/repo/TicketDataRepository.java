package lk.ijse.gdse.wanderlust.repo;

import lk.ijse.gdse.wanderlust.entity.TicketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketDataRepository extends JpaRepository<TicketData,Long> {
    List<TicketData> findAll();


    @Query(value = """
    SELECT * 
    FROM ticket_preview 
    WHERE (:departureAirport IS NULL OR departure_airport LIKE %:departureAirport%)
      AND (:arrivalAirport IS NULL OR arrival_airport LIKE %:arrivalAirport%) 
      AND (:arrivalDate IS NULL OR arrival_date = :arrivalDate)
      AND (:departureDate IS NULL OR departure_date = :departureDate)
      AND (:ticketType IS NULL OR ticket_type LIKE %:ticketType%)
    """, nativeQuery = true)

    List<TicketData> searchAllTickets(
            String departureAirport,
            String arrivalAirport,
            String arrivalDate,
            String departureDate,
            String ticketType
    );
}
