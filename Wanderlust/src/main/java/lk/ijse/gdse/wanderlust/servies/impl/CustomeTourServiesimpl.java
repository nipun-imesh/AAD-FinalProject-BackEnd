package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.CustomTourDTO;
import lk.ijse.gdse.wanderlust.entity.CustomTour;
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

    public int saveTour(CustomTourDTO customeTourDTO) {
        try {
            customeTourRepository.save(modelMapper.map(customeTourDTO, CustomTour.class));
            return StatusList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusList.Internal_Server_Error;
        }
    }

    @Override
    public int updateTour(CustomTourDTO customeTourDTO) {
        try {
            if( customeTourRepository.existsById(String.valueOf(customeTourDTO.getId()))){
                customeTourRepository.save(modelMapper.map(customeTourDTO, CustomTour.class));
                return StatusList.Created;
            }else {
                return StatusList.Not_Found;
            }
        } catch (Exception e) {
            return StatusList.Internal_Server_Error;
        }
    }

    @Override
    public List<CustomTourDTO> getAllTours() {
        List<CustomTour> all = customeTourRepository.findAll();
        return modelMapper.map(all,new TypeToken<List<CustomTourDTO>>(){}.getType());
    }

    @Override
    public List<CustomTourDTO> searchTours(String keyword) {
        List<CustomTour> tours = customeTourRepository.searchByKeyword(keyword);
        return modelMapper.map(tours,new TypeToken<List<CustomTourDTO>>(){}.getType());
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
