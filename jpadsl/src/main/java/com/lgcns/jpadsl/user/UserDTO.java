package com.lgcns.jpadsl.user;

import com.lgcns.jpadsl.common.CardNoSerializer;
import com.lgcns.jpadsl.common.EnumValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tools.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;

    @Email(message = "올바른 이메일 형식이 아닙니다!", groups = OnCreate.class)
    private String email;

    @Size(min = 8, message = "암호는 8글자 이상 입력하세요!", groups = OnCreate.class)
    private String passwd;

    @NotBlank(message = "닉네임을 입력하세요!")
    private String nickname;

    @EnumValue(enumClass = BloodType.class, message = "올바른 혈액형이 아닙니다!")
    private BloodType bloodType;

//    @JsonSerialize(using = TelnoSerializer.class)
//    private String telno;

    @JsonSerialize(using = CardNoSerializer.class)
    private String creditCard;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public interface OnCreate {
    }

    public interface OnUpdate {
    }
}
