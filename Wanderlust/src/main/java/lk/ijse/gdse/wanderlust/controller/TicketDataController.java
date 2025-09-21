package lk.ijse.gdse.wanderlust.controller;

import lk.ijse.gdse.wanderlust.dto.ResponsDto;
import lk.ijse.gdse.wanderlust.dto.TicketDataDTO;
import lk.ijse.gdse.wanderlust.servies.TicketDataServices;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticketData")
@CrossOrigin
public class TicketDataController {

    @Autowired
    private TicketDataServices ticketDataServices;

    @GetMapping("/getticketdata")
    public ResponseEntity<ResponsDto> getTicketData() {
        try {
            List<TicketDataDTO> allTickets = ticketDataServices.getAllTicketData();

            if (allTickets != null && !allTickets.isEmpty()) {

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponsDto(StatusList.OK, "Tickets retrieved successfully", allTickets
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(StatusList.Not_Found, "No tickets found", null
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null
                    ));
        }
    }

    @GetMapping("/searchTicket")
    public ResponseEntity<ResponsDto> searchTicket(
            @RequestParam String departureAirport,
            @RequestParam String arrivalAirport,
            @RequestParam String departureDate,
            @RequestParam(required = false) String arrivalDate,
            @RequestParam String travel_class
    ) {
        try {
            // Build DTO from query params
            TicketDataDTO ticketDataDTO = new TicketDataDTO();
            ticketDataDTO.setDepartureAirport(departureAirport);
            ticketDataDTO.setArrivalAirport(arrivalAirport);
            ticketDataDTO.setDepartureDate(departureDate);
            ticketDataDTO.setArrivalDate(arrivalDate);
            ticketDataDTO.setTravelClass(travel_class);

            List<TicketDataDTO> allTickets = ticketDataServices.searchTicket(ticketDataDTO);

            if (allTickets != null && !allTickets.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponsDto(StatusList.OK, "Tickets retrieved successfully", allTickets));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(StatusList.Not_Found, "No tickets found", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, "fail to search", null));
        }
    }
}
