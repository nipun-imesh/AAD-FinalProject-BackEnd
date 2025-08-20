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

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
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
            String role = userServies.getUserRoleByEmail(userDTO.getEmail());
            String token = jwtUtil.generateToken(userDTO);

            AuthaDTO authaDTO = new AuthaDTO(token, userDTO.getEmail(), role);

            switch (res) {
                case StatusList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponsDto(StatusList.Created, "success", authaDTO));
                case StatusList.Not_Acceptable:
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponsDto(StatusList.Not_Acceptable, "fail", null));
                case StatusList.Unauthorized:
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new ResponsDto(StatusList.Unauthorized, "fail", null));
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
}
