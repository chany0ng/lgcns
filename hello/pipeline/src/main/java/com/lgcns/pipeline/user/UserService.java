package com.lgcns.pipeline.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO regist(UserRegistDTO dto) {
        User user = mapper.toEntity(dto);
        user.setPasswd(passwordEncoder.encode(dto.getPasswd()));
        user.addRole(UserRole.ROLE_USER);

        User newer = repository.save(user);
        return mapper.toDTO(newer);
    }

    public UserDTO getUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found!!"));

        return mapper.toDTO(user);
    }

    public UserDTO editUser(Long id, UserEditDTO dto) {
        User user = repository.findById(id).orElseThrow();
        user.setName(dto.getName());
        return mapper.toDTO(repository.save(user));
    }

    @Transactional
    public UserDTO editRoles(Long id, UserRolesDTO dto) {
        User user = repository.findByIdForUpdate(id).orElseThrow();
        user.clearRoles();
        dto.getRoles().forEach(user::addRole);
        repository.save(user);
        return mapper.toDTO(repository.getWithRoles(user.getEmail()).orElseThrow());
    }
}
