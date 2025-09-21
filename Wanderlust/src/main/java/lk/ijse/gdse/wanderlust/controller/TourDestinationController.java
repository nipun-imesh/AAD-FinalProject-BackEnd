package lk.ijse.gdse.wanderlust.controller;

import lk.ijse.gdse.wanderlust.dto.ResponsDto;
import lk.ijse.gdse.wanderlust.dto.TourDestinationDTO;
import lk.ijse.gdse.wanderlust.servies.TourDestinationServices;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tourDestination")
@CrossOrigin
public class TourDestinationController {

    private final TourDestinationServices tourDestinationServices;

    public TourDestinationController(TourDestinationServices tourDestinationServices) {
        this.tourDestinationServices = tourDestinationServices;
    }

    @PostMapping("saveTourDestination")
    public ResponseEntity<ResponsDto> addTourDestination(@RequestBody TourDestinationDTO tourDestinationDTO){
        System.out.println(tourDestinationDTO.getDestinationimage()+"nipunqqqqqqqqqq");
        try {
            int res = tourDestinationServices.addTourDestination(tourDestinationDTO);
            switch (res) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("AllgetTourDestination")
    public ResponseEntity<ResponsDto> getTourDestination(){
        try {
            List<TourDestinationDTO> tourDestinationDTOList = tourDestinationServices.getTourDestination();
            System.out.println(tourDestinationDTOList.size());
            System.out.println(tourDestinationDTOList +" aaaaaaaaaasdasdadad");
            if (tourDestinationDTOList != null && !tourDestinationDTOList.isEmpty()) {

                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponsDto(StatusList.OK, "Destination retrieved successfully", tourDestinationDTOList
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(StatusList.Not_Found, "No Destination found", null
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null
                    ));
        }
    }

    @DeleteMapping("deleteTourDestination/{id}")
    public ResponseEntity<ResponsDto> deleteTourDestination(@PathVariable int id){
        try {
            int res = tourDestinationServices.deleteTourDestination(id);
            switch (res) {
                case StatusList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponsDto(StatusList.Created, "Deleted success", null));
                case StatusList.Not_Acceptable:
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponsDto(StatusList.Not_Acceptable, "fail", null));
                default:
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponsDto(StatusList.Bad_Gateway, "fail", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PatchMapping("updateTourDestination/{id}")
    public ResponseEntity<ResponsDto> updateTourDestination(@PathVariable int id, @RequestBody TourDestinationDTO tourDestinationDTO){
        try {
            int res = tourDestinationServices.updateTourDestination(id, tourDestinationDTO);
            switch (res) {
                case StatusList.Created:
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponsDto(StatusList.OK, "Updated successfully", null));

                case StatusList.Not_Acceptable:
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponsDto(StatusList.Not_Acceptable, "fail", null));
                default:
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponsDto(StatusList.Bad_Gateway, "fail", null));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
