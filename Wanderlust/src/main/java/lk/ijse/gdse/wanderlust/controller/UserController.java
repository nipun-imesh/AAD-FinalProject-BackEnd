package lk.ijse.gdse.wanderlust.controller;


import lk.ijse.gdse.wanderlust.dto.AuthaDTO;
import lk.ijse.gdse.wanderlust.dto.ResponsDto;
import lk.ijse.gdse.wanderlust.dto.UserDTO;
import lk.ijse.gdse.wanderlust.servies.impl.UserServiesimpl;
import lk.ijse.gdse.wanderlust.util.JwtUtil;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://127.0.0.1:5501") // allow your frontend

public class UserController {

    private final UserServiesimpl userServies;

    private final JwtUtil jwtUtil;

    public UserController(UserServiesimpl userServies, JwtUtil jwtUtil) {
        this.userServies = userServies;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponsDto> register(@RequestBody UserDTO userDTO){

        try {
            int res = userServies.saveUser(userDTO);
            String token = jwtUtil.generateToken(userDTO);
            AuthaDTO authaDTO = new AuthaDTO(token,userDTO.getEmail());

            switch (res){
                case StatusList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponsDto(StatusList.Created,"success",authaDTO));
                case StatusList.Not_Acceptable:
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponsDto(StatusList.Not_Acceptable,"fail",null));
                default:
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponsDto(StatusList.Bad_Gateway,"fail",null));
            }
        } catch (Exception e) {
            e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error,e.getMessage(),null));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ResponsDto> login(@RequestBody UserDTO userDTO) {
        try {
            int res = userServies.loginUser(userDTO);

            if (res == StatusList.Created) {
                String role = userServies.getUserRoleByEmail(userDTO.getEmail());
                String token = jwtUtil.generateToken(userDTO);

                AuthaDTO authaDTO = new AuthaDTO(token, userDTO.getEmail(), role);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponsDto(StatusList.Created, "Login success", authaDTO));
            }
            else if (res == StatusList.Unauthorized) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponsDto(StatusList.Unauthorized, "Invalid password", null));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ResponsDto(StatusList.Not_Acceptable, "Email not found", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(StatusList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/getUser/{email}")
    public ResponseEntity<ResponsDto> getUser(@PathVariable String email) {
        try {
            UserDTO userDTO = userServies.getUser(email);

            if (userDTO != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponsDto(
                                StatusList.Created,
                                "User found",
                                userDTO
                        ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponsDto(
                                StatusList.Not_Found,
                                "User not found",
                                null
                        ));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(
                            StatusList.Internal_Server_Error,
                            e.getMessage(),
                            null
                    ));
        }
    }

    @GetMapping("/AllgetUser")
    public ResponseEntity<ResponsDto> getAllUser() {
        try {
            // Get all users from service
            var allUsers = userServies.getAllUser();

            // Return response
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponsDto(
                            StatusList.Created,
                            "Success",
                            allUsers
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponsDto(
                            StatusList.Internal_Server_Error,
                            e.getMessage(),
                            null
                    ));
        }
    }

}
