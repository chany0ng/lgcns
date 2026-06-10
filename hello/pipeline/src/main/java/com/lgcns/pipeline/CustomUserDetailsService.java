package com.lgcns.pipeline;

import com.lgcns.pipeline.user.User;
import com.lgcns.pipeline.user.UserMapper;
import com.lgcns.pipeline.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@NullMarked
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("*** Service.loadUserByUsername.email=" + username);
        User user = repository.getWithRoles(username).orElseThrow();
        System.out.println("user = " + user);
        return mapper.toDTO(user);
    }

}
