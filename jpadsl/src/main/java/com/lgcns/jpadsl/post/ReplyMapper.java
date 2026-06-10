package com.lgcns.jpadsl.post;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    ReplyDTO toDTO(Reply reply);

    Reply toEntity(ReplyDTO dto);

    Reply toEntity(ReplySaveDTO dto);

    List<ReplyDTO> toDTOList(List<Reply> replies);
}
