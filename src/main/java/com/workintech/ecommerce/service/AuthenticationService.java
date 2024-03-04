package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.ApplicationUser;
import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.entity.Store;
import com.workintech.ecommerce.exceptions.CategoryException;
import com.workintech.ecommerce.exceptions.ProductException;
import com.workintech.ecommerce.repository.RoleRepository;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApplicationUser register(String fullName, String email, String password, Long role_id, Store store){
        if(userRepository.findByEmail(email).isPresent()){
            throw new ProductException("email already signed!",HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();;
        ApplicationUser user = new ApplicationUser();

        if(role_id == 1){
            userRole = roleRepository.findByAuthority("ADMIN").get();
        }
        if(role_id == 2){
            userRole = roleRepository.findByAuthority("STORE").get();
            user.setStore(store);
        }


        Set<Role> roles = new HashSet<>();
        roles.add(userRole);


        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(encodedPassword);
        user.setRoles(roles);


        return userRepository.save(user);
    }
}
