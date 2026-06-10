package com.lgcns.pipeline.memo;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoMapper {
    MemoDTO toDTO(Memo memo);

    List<MemoDTO> toDTOList(List<Memo> memos);
}
