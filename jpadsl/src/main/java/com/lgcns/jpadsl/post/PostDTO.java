package com.lgcns.jpadsl.post;

import java.util.List;

public record PostDTO(Long id, String title, String writer, PostBodyDTO body, List<ReplyDTO> replies,
                      List<HashtagDTO> hashtags) {
}
