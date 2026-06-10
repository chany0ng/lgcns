package com.lgcns.jpadsl.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDTO> getUsers() {
        List<User> users = repository.findAll();

        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(mapper.toDTO(user));
        }

        return dtos;
    }

    public UserDTO regist(UserDTO dto) {
        User newer = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(newer);
    }


    public UserDTO getUser(Long userid) {
        User user = repository.findById(userid).orElseThrow(() -> new IllegalArgumentException("User not found!"));

        return mapper.toDTO(user);
    }

    @Transactional
    public UserDTO editUser(UserDTO dto) {
        User user = repository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("Not found user!!"));
        user.setNickname(dto.getNickname());
        user.setBloodType(dto.getBloodType());
//        repository.save(user);

        return mapper.toDTO(user);
    }

    // 비관적 락을 위한 메소드
    @Transactional
    public UserDTO updateUser(UserDTO dto) {
        User user = repository.findByEmailForUpdate(dto.getEmail()).orElseThrow();

        user.setNickname(dto.getNickname());
        user.setBloodType(dto.getBloodType());
        repository.save(user);

        return mapper.toDTO(user);
    }

    public void deleteUser(Long userid) {
        User user = repository.findById(userid).orElseThrow();
        repository.delete(user);
    }
}
