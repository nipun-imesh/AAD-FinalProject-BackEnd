package lk.ijse.gdse.wanderlust.servies;

import lk.ijse.gdse.wanderlust.dto.TourDestinationDTO;

import java.util.List;

public interface TourDestinationServices {
    int addTourDestination(TourDestinationDTO tourDestinationDTO);

    List<TourDestinationDTO> getTourDestination();

    int deleteTourDestination(int id);

    int updateTourDestination(int id, TourDestinationDTO tourDestinationDTO);
}
