package lk.ijse.gdse.wanderlust.servies;

import lk.ijse.gdse.wanderlust.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public interface UserServices {
    Set<SimpleGrantedAuthority> getAuthority(User user);
}
