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
    WHERE (:departureAirport IS NULL OR departure_airport = :departureAirport)
      AND (:arrivalAirport IS NULL OR arrival_airport = :arrivalAirport)
      AND (:departureDate IS NULL OR departure_date = :departureDate)
      AND (:arrivalDate IS NULL OR arrival_date = :arrivalDate)
      AND (:travelClass IS NULL OR travel_class = :travelClass)
""", nativeQuery = true)
    List<TicketData> searchAllTickets(
            String departureAirport,
            String arrivalAirport,
            String departureDate,
            String arrivalDate,
            String travelClass
    );

}
