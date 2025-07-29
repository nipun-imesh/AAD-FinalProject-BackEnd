package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.CustomeTourDTO;
import lk.ijse.gdse.wanderlust.entity.CustomeTour;
import lk.ijse.gdse.wanderlust.repo.CustomeTourRepository;
import lk.ijse.gdse.wanderlust.servies.CostomerTourServies;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomeTourServiesimpl implements CostomerTourServies {

    @Autowired
    private CustomeTourRepository customeTourRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int saveTour(CustomeTourDTO customeTourDTO) {
        try {
            customeTourRepository.save(modelMapper.map(customeTourDTO, CustomeTour.class));
            return StatusList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusList.Internal_Server_Error;
        }
    }

    @Override
    public int updateTour(CustomeTourDTO customeTourDTO) {
        try {
            if( customeTourRepository.existsById(String.valueOf(customeTourDTO.getId()))){
                customeTourRepository.save(modelMapper.map(customeTourDTO, CustomeTour.class));
                return StatusList.Created;
            }else {
                return StatusList.Not_Found;
            }
        } catch (Exception e) {
            return StatusList.Internal_Server_Error;
        }
    }

    @Override
    public List<CustomeTourDTO> getAllTours() {
        List<CustomeTour> all = customeTourRepository.findAll();
        return modelMapper.map(all,new TypeToken<List<CustomeTourDTO>>(){}.getType());
    }

    @Override
    public List<CustomeTourDTO> searchTours(String keyword) {
        List<CustomeTour> tours = customeTourRepository.searchByKeyword(keyword);
        return modelMapper.map(tours,new TypeToken<List<CustomeTourDTO>>(){}.getType());
    }

    @Override
    public Object deleteTour(String id) {
        try {
            customeTourRepository.deleteById(id);
            return StatusList.OK;
        } catch (Exception e) {
            return StatusList.Bad_Gateway;
        }
    }
}
