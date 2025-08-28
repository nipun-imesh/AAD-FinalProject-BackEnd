package lk.ijse.gdse.wanderlust.controller;

import lk.ijse.gdse.wanderlust.dto.CustomTourDTO;
import lk.ijse.gdse.wanderlust.dto.ResponsDto;
import lk.ijse.gdse.wanderlust.servies.CostomerTourServies;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/customerTour")
public class CustomTourController {

    private final CostomerTourServies customeTourServies;

    public CustomTourController(CostomerTourServies customeTourServies) {
        this.customeTourServies = customeTourServies;
    }

    @PostMapping("/addTour")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<ResponsDto> addTour(@RequestBody CustomTourDTO customeTourDTO) {
        try {
            int res = customeTourServies.saveTour(customeTourDTO);

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

    @PatchMapping("/updateTour")
    public ResponseEntity<ResponsDto> updateTour(@RequestBody CustomTourDTO customeTourDTO) {
        try {
            int res = customeTourServies.updateTour(customeTourDTO);

            switch (res) {
                case StatusList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponsDto(StatusList.Created, "success", null));
                case StatusList.Not_Acceptable:
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponsDto(StatusList.Not_Acceptable, "fail", null));
                default:
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponsDto(StatusList.Bad_Gateway, "not found", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/AllgetTours")
    public ResponseEntity<ResponsDto> getTours() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponsDto(StatusList.Created, "success", customeTourServies.getAllTours()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ResponsDto> searchTours(@PathVariable String keyword) {
        try {
            List<CustomTourDTO> results = customeTourServies.searchTours(keyword);

            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(StatusList.Not_Found, "No results found", null));
            } else {
                return ResponseEntity.ok(
                        new ResponsDto(StatusList.Created, "Success", results));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @DeleteMapping("/deleteTour/{id}")
    public ResponseEntity<ResponsDto> deleteTour(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponsDto(StatusList.Created, "success", customeTourServies.deleteTour(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
