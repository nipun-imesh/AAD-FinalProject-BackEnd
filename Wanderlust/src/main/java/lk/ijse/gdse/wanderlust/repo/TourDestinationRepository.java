package lk.ijse.gdse.wanderlust.repo;

import lk.ijse.gdse.wanderlust.entity.TourDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourDestinationRepository extends JpaRepository<TourDestination, Long> {

}
