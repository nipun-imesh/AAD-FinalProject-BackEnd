package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.TicketBookingDTO;
import lk.ijse.gdse.wanderlust.entity.TicketBooking;
import lk.ijse.gdse.wanderlust.repo.TicketBookingRepository;
import lk.ijse.gdse.wanderlust.servies.TicketBookingService;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingServiceimpl implements TicketBookingService {

    @Autowired
    private final TicketBookingRepository ticketBookingRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public TicketBookingServiceimpl(TicketBookingRepository ticketBookingRepository, ModelMapper modelMapper) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public int saveTicketBooking(TicketBookingDTO ticketBookingDTO) {
        try {
            ticketBookingRepository.save(modelMapper.map(ticketBookingDTO, TicketBooking.class));
            return StatusList.Created;
        } catch (Exception e) {
            e.printStackTrace();
            return StatusList.Internal_Server_Error;
        }
    }
}
