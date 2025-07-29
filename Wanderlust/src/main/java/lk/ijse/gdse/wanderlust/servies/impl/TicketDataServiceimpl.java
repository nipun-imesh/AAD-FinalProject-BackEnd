package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.TicketDataDTO;
import lk.ijse.gdse.wanderlust.entity.TicketData;
import lk.ijse.gdse.wanderlust.repo.TicketDataRepository;
import lk.ijse.gdse.wanderlust.servies.TicketDataServices;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketDataServiceimpl implements TicketDataServices {

    @Autowired
    private TicketDataRepository ticketDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TicketDataDTO> getAllTicketData() {
        List<TicketData> all = ticketDataRepository.findAll();
        return modelMapper.map(all,new TypeToken<List<TicketDataDTO>>(){}.getType());
    }
}
