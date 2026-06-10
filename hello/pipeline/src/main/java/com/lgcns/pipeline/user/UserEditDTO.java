package com.lgcns.pipeline.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserEditDTO {
    @NotBlank
    private String name;
}
