package lk.ijse.gdse.wanderlust.servies;

import lk.ijse.gdse.wanderlust.dto.CustomeTourDTO;

import java.util.List;

public interface CostomerTourServies {
    int saveTour(CustomeTourDTO customeTourDTO);

    int updateTour(CustomeTourDTO customeTourDTO);

    List<CustomeTourDTO> getAllTours();

    List<CustomeTourDTO> searchTours(String keyword);

    Object deleteTour(String id);
}
