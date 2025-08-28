package lk.ijse.gdse.wanderlust.servies.impl;

import lk.ijse.gdse.wanderlust.dto.UserDTO;
import lk.ijse.gdse.wanderlust.entity.User;
import lk.ijse.gdse.wanderlust.repo.UserRepository;
import lk.ijse.gdse.wanderlust.servies.UserService;
import lk.ijse.gdse.wanderlust.util.StatusList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiesimpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveUser(UserDTO userDTO) {
        if(userRepo.existsByEmail(userDTO.getEmail())) {
            return StatusList.Not_Acceptable;
        }else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            userRepo.save(modelMapper.map(userDTO, User.class));
            return StatusList.Created;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public boolean ifEmailExists(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDTO searchUser(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }


    @Override
    public int resetPass(UserDTO exuser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        exuser.setPassword(encoder.encode(exuser.getPassword()));
        userRepo.save(modelMapper.map(exuser,User.class));
        return StatusList.Created;
    }

    @Override
    public String getUserRoleByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user.getRole();
    }

    @Override
    public int loginUser(UserDTO userDTO) {
        Optional<User> user = Optional.ofNullable(userRepo.findByEmail(userDTO.getEmail()));

        if (user.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println("Plain: " + userDTO.getPassword());
            System.out.println("Hashed in DB: " + user.get().getPassword());

            if (encoder.matches(userDTO.getPassword(), user.get().getPassword())) {
                System.out.println("Password matched!");
                return StatusList.Created; // login success
            } else {
                System.out.println("Password mismatch!");
                return StatusList.Unauthorized; // wrong password
            }
        }
        return StatusList.Not_Acceptable; // email not found
    }
}
