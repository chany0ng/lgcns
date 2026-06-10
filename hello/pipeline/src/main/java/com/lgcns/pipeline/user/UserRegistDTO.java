package com.lgcns.pipeline.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistDTO {
    @Email
    private String email;

    @Size(min = 8, max = 20, message = "암호는 8자리 이상 20자리 이하로 입력하세요!")
    private String passwd;

    @NotBlank
    private String name;
}
