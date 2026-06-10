package com.lgcns.jpadsl.post;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDTO toDTO(Hashtag hashtag);

    Hashtag toEntity(HashtagDTO dto);

    List<HashtagDTO> toDTOList(List<Hashtag> hashtags);
}
