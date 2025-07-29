package lk.ijse.gdse.wanderlust.repo;

import lk.ijse.gdse.wanderlust.entity.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, String> {
}
