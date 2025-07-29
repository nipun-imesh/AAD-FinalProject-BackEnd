package lk.ijse.gdse.wanderlust.controller;

import lk.ijse.gdse.wanderlust.dto.ResponsDto;
import lk.ijse.gdse.wanderlust.dto.TicketBookingDTO;
import lk.ijse.gdse.wanderlust.servies.impl.TicketBookingServiceimpl;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticketBooking")
public class TicketBookingController {

    private final TicketBookingServiceimpl ticketBookingService;

    public TicketBookingController(TicketBookingServiceimpl ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    @PostMapping("/saveTicketBooking")
    public ResponseEntity<ResponsDto> saveTicketBooking(@RequestBody TicketBookingDTO ticketBookingDTO) {
        try {
            int ser = ticketBookingService.saveTicketBooking(ticketBookingDTO);

            switch (ser) {
                case StatusList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponsDto(StatusList.Created, "success", null));
                case StatusList.Not_Acceptable:
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponsDto(StatusList.Not_Acceptable, "fail", null));
                default:
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponsDto(StatusList.Bad_Gateway, "fail", null));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, "fail", null));
        }
    }
}
