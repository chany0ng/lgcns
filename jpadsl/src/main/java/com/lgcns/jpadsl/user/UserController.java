package com.lgcns.jpadsl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> regist(@RequestBody @Validated(UserDTO.OnCreate.class) UserDTO dto) {
        return ResponseEntity.ok(service.regist(dto));
    }

    @GetMapping("{userid}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userid) {
        return ResponseEntity.ok(service.getUser(userid));
    }

    @PutMapping("{userid}")
    public ResponseEntity<UserDTO> editUser(@PathVariable Long userid, @RequestBody @Validated(UserDTO.OnUpdate.class) UserDTO dto) {
        dto.setId(userid);
        return ResponseEntity.ok(service.editUser(dto));
    }

    @DeleteMapping("{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userid) {
        service.deleteUser(userid);
        return ResponseEntity.noContent().build();
    }
}
