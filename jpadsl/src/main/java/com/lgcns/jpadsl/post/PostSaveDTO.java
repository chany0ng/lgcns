package com.lgcns.jpadsl.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PostSaveDTO {
    @Positive(groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "제목은 필수값입니다!")
    private String title;

    @NotBlank(groups = OnCreate.class)
    private String writer;

    private PostBodyDTO body;

    private List<Hashtag> hashtags;

    public interface OnCreate {
    }

    public interface OnUpdate {
    }
}
