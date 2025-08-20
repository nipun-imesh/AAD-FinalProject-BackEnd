package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.OfferAddDTO;
import lk.ijse.gdse.wanderlust.entity.OfferAdd;
import lk.ijse.gdse.wanderlust.repo.OfferAddRepository;
import lk.ijse.gdse.wanderlust.servies.OfferAddServices;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferAddServiceimpl implements OfferAddServices {

    private final OfferAddRepository offerAddRepository;

    private final ModelMapper modelMapper;

    public OfferAddServiceimpl(OfferAddRepository offerAddRepository, ModelMapper modelMapper) {
        this.offerAddRepository = offerAddRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public int saveOfferAdd(OfferAddDTO offerAddDTO) {
        try {
            offerAddRepository.save(modelMapper.map(offerAddDTO, OfferAdd.class));
            return StatusList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusList.Internal_Server_Error;
        }
    }

    @Override
    public Object getAllOfferAdd() {
        try {
            return offerAddRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return StatusList.Internal_Server_Error;
        }
    }

    @Override
    public Object deleteOfferAdd(String id) {
        try {
            offerAddRepository.deleteById(Long.valueOf(id));
            return StatusList.OK;
        } catch (Exception e) {
            return StatusList.Bad_Gateway;
        }
    }

    @Override
    public Object updateOfferAdd(OfferAddDTO offerAddDTO) {
        try {
            offerAddRepository.save(modelMapper.map(offerAddDTO, OfferAdd.class));
            return StatusList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusList.Internal_Server_Error;
        }
    }
}
