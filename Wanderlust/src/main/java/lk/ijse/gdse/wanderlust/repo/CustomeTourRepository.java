package lk.ijse.gdse.wanderlust.repo;

import lk.ijse.gdse.wanderlust.entity.CustomeTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomeTourRepository extends JpaRepository<CustomeTour, String> {

    @Query("SELECT t FROM CustomeTour t WHERE " +
            "LOWER(t.country) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.date) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.duration) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<CustomeTour> searchByKeyword(@Param("keyword") String keyword);

}
