package com.lgcns.jpadsl.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostEditDTO {
    private Long id;
    private String title;
    private String contents;
}