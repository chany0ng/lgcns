package com.lgcns.pipeline.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(description = "로그인 요청")
public record LoginRequestDTO(
        @Email
        @Schema(description = "이메일", example = "admin@gmail.com")
        String username,

        @Size(min = 8)
        @Schema(description = "비밀번호", example = "password123")
        String password
) {
}
