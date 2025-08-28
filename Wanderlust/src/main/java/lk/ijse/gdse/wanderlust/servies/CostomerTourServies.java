package lk.ijse.gdse.wanderlust.servies;

import lk.ijse.gdse.wanderlust.dto.CustomTourDTO;

import java.util.List;

public interface CostomerTourServies {
    int saveTour(CustomTourDTO customeTourDTO);

    int updateTour(CustomTourDTO customeTourDTO);

    List<CustomTourDTO> getAllTours();

    List<CustomTourDTO> searchTours(String keyword);

    Object deleteTour(String id);
}
