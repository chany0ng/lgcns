package com.lgcns.pipeline.user;

import com.lgcns.pipeline.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping()
    public ResponseEntity<UserDTO> regist(@RequestBody @Valid UserRegistDTO dto) {
        return ResponseEntity.ok(service.regist(dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    @Tag(name = "고객 정보 얻기", description = "API 설명 부분...")
    @Operation(summary = "URL 링크 설명", description = "펼쳤을 때 설명 부분...")
    @Parameters({
            @Parameter(name = "id", description = "유저 아이디", example = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청에 성공하였습니다.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "해당 유저가 없습니다.")
    })
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("profile")
    public ResponseEntity<UserDTO> getProfile(Authentication auth) {
        UserDTO dto = (UserDTO) auth.getPrincipal();
        if (dto == null) throw new JwtException("Not valid user!!");

        return ResponseEntity.ok(service.getUser(dto.getId()));
    }

    @PutMapping("edit-profile")
    public ResponseEntity<UserDTO> editProfile(Authentication auth, @RequestBody @Valid UserEditDTO dto) {
        UserDTO loginUser = (UserDTO) auth.getPrincipal();
        if (loginUser == null) throw new JwtException("Not valid user!!");

        return ResponseEntity.ok(service.editUser(loginUser.getId(), dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/{id}/roles")
    public ResponseEntity<UserDTO> editRoles(Authentication auth, @PathVariable Long id, @RequestBody @Valid UserRolesDTO dto) {
        UserDTO loginUser = (UserDTO) auth.getPrincipal();
        if (loginUser == null || Objects.equals(loginUser.getId(), id))
            throw new JwtException("Cannot edit your own roles!");

        return ResponseEntity.ok(service.editRoles(id, dto));
    }

    @Operation(summary = "로그인",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = LoginRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공"),
                    @ApiResponse(responseCode = "401", description = "인증 실패")
            }
    )
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void login(@RequestBody LoginRequestDTO request) {
        // 실제로 실행되지 않음 - Security 필터가 먼저 처리!
        throw new UnsupportedOperationException("handled by Spring Security");
    }

}
