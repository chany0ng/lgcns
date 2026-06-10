package com.lgcns.jpadsl.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReplySaveDTO {
    @Positive(groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "댓글은 필수값입니다!")
    private String reply;

    @NotBlank()
    private String replier;

    public interface OnCreate {
    }

    public interface OnUpdate {
    }
}
