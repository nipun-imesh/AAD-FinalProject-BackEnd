package lk.ijse.gdse.wanderlust.servies;


import lk.ijse.gdse.wanderlust.dto.UserDTO;
import lk.ijse.gdse.wanderlust.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public interface UserService {
     int saveUser(UserDTO userDTO) ;

     UserDetails loadUserByUsername(String email);

     UserDTO loadUserDetailsByUsername(String username);
     boolean ifEmailExists(String email) ;

     UserDTO searchUser(String email);
     int resetPass(UserDTO exuser);

    String getUserRoleByEmail(String email);

    int loginUser(UserDTO userDTO);

    UserDTO getUser(String email);

    Object getAllUser();
}