package com.lgcns.pipeline.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserRolesDTO {
    @Size(min = 1)
    private List<UserRole> roles;
}
