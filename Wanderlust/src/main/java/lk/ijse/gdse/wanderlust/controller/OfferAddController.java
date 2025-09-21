//package lk.ijse.gdse.wanderlust.controller;
//
//import lk.ijse.gdse.wanderlust.dto.OfferAddDTO;
//import lk.ijse.gdse.wanderlust.dto.ResponsDto;
//import lk.ijse.gdse.wanderlust.servies.OfferAddServices;
//import lk.ijse.gdse.wanderlust.util.StatusList;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin
//@RestController
//@RequestMapping("api/v1/offerAdd")
//public class OfferAddController {
//
//    private final OfferAddServices offerAddServices;
//
//    public OfferAddController(OfferAddServices offerAddServices) {
//        this.offerAddServices = offerAddServices;
//    }
//
//    @PostMapping( "/saveOfferAdd")
//    public ResponseEntity<ResponsDto> saveOfferAdd(@RequestBody OfferAddDTO offerAddDTO){
//        try {
//            int res = offerAddServices.saveOfferAdd(offerAddDTO);
//            switch (res) {
//                case StatusList.Created:
//                    return ResponseEntity.status(HttpStatus.CREATED)
//                            .body(new ResponsDto(StatusList.Created, "success", null));
//                case StatusList.Not_Acceptable:
//                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
//                            .body(new ResponsDto(StatusList.Not_Acceptable, "fail", null));
//                default:
//                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
//                            .body(new ResponsDto(StatusList.Bad_Gateway, "not found", null));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
//        }
//    }
//
//    @GetMapping("/getAllOfferAdd")
//    public ResponseEntity<ResponsDto> getAllOfferAdd(){
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new ResponsDto(StatusList.Created, "success", offerAddServices.getAllOfferAdd()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponsDto(StatusList.Internal_Server_Error, "get unsuccess", null));
//        }
//    }
//
//    @DeleteMapping("/deleteOfferAdd/{id}")
//    public ResponseEntity<ResponsDto> deleteOfferAdd(@PathVariable String id){
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new ResponsDto(StatusList.Created, "success", offerAddServices.deleteOfferAdd(id)));
//
//        }   catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponsDto(StatusList.Internal_Server_Error, "delete unsuccess", null));
//        }
//    }
//
//    @PutMapping("/updateOfferAdd")
//    public ResponseEntity<ResponsDto> updateOfferAdd(@RequestBody OfferAddDTO offerAddDTO){
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(new ResponsDto(StatusList.Created, "success", offerAddServices.updateOfferAdd(offerAddDTO)));
//        }   catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponsDto(StatusList.Internal_Server_Error, "update fail", null));
//        }
//    }
//}
