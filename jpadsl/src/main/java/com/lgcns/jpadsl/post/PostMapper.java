package com.lgcns.jpadsl.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toDTO(Post entity);

    @Mapping(target = "body", ignore = true)
    @Mapping(target = "replies", ignore = true)
    Post toEntity(PostDTO dto);

    PostBodyDTO toDTO(PostBody body);

    PostBody toEntity(PostBodyDTO dto);
}
