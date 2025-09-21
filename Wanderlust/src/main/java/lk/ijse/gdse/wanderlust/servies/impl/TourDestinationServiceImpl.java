package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.TicketDataDTO;
import lk.ijse.gdse.wanderlust.dto.TourDestinationDTO;
import lk.ijse.gdse.wanderlust.entity.TourDestination;
import lk.ijse.gdse.wanderlust.repo.TourDestinationRepository;
import lk.ijse.gdse.wanderlust.servies.TourDestinationServices;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // ✅ tells Spring to register this as a bean
public class TourDestinationServiceImpl implements TourDestinationServices {

    private final TourDestinationRepository tourDestinationRepository;

    private final ModelMapper modelMapper;

    public TourDestinationServiceImpl(TourDestinationRepository tourDestinationRepository, ModelMapper modelMapper) {
        this.tourDestinationRepository = tourDestinationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public int addTourDestination(TourDestinationDTO dto) {

        TourDestination entity = new TourDestination();
        entity.setId(null);
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setDescription(dto.getDescription());
        entity.setDestinationimage(dto.getDestinationimage());
        entity.setStatus(dto.getStatus());
        entity.setLongitude(dto.getLongitude());
        entity.setLatitude(dto.getLatitude());

        tourDestinationRepository.save(entity);
        return StatusList.Created;
    }

    @Override
    public List<TourDestinationDTO> getTourDestination() {
        List<TourDestination> tourDestinationList = tourDestinationRepository.findAll();
        return modelMapper.map(tourDestinationList,new TypeToken<List<TourDestinationDTO>>(){}.getType());
    }

    @Override
    public int deleteTourDestination(int id) {
        tourDestinationRepository.deleteById((long) id);
        return StatusList.Created;
    }

    @Override
    public int updateTourDestination(int id, TourDestinationDTO tourDestinationDTO) {
        TourDestination entity = tourDestinationRepository.findById((long) id).get();
        entity.setCountry(tourDestinationDTO.getCountry());
        entity.setCity(tourDestinationDTO.getCity());
        entity.setDescription(tourDestinationDTO.getDescription());
        entity.setDestinationimage(tourDestinationDTO.getDestinationimage());
        entity.setStatus(tourDestinationDTO.getStatus());
        entity.setLongitude(tourDestinationDTO.getLongitude());
        entity.setLatitude(tourDestinationDTO.getLatitude());

        tourDestinationRepository.save(entity);
        return StatusList.Created;
    }
}
