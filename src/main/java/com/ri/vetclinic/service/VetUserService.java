package com.ri.vetclinic.service;

import com.ri.vetclinic.model.VetUser;
import com.ri.vetclinic.model.repository.VetUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class VetUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final VetUserRepository vetUserRepository;

    public VetUserService(PasswordEncoder passwordEncoder, VetUserRepository vetUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.vetUserRepository = vetUserRepository;
    }

    public void register(String username, String password) {
        VetUser vetUser = new VetUser();
        vetUser.setUsername(username);
        vetUser.setPassword(passwordEncoder.encode(password));
        vetUserRepository.save(vetUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VetUser user = vetUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
