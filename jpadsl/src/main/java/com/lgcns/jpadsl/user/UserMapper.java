package com.lgcns.jpadsl.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "passwd", ignore = true)
    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);
}
