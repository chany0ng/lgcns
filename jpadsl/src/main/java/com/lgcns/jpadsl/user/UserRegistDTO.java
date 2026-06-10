package com.lgcns.jpadsl.user;

import com.lgcns.jpadsl.common.Telno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistDTO {
    @Email(message = "올바른 이메일 형식이 아닙니다!")
    private String email;

    @Size(min = 8, message = "암호는 8글자 이상 입력하세요!")
    private String passwd;

    @NotBlank(message = "닉네임을 입력하세요!")
    private String nickname;

    @Telno
    private String telno;
}
