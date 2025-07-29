package lk.ijse.gdse.wanderlust.repo;

import lk.ijse.gdse.wanderlust.entity.TicketData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketDataRepository extends JpaRepository<TicketData,Long> {
    List<TicketData> findAll();
}
