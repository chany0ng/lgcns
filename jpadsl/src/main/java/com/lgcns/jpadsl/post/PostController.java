package com.lgcns.jpadsl.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping()
    public ResponseEntity<List<PostDTO>> getList(@RequestParam(required = false) Integer page) {
        if (page == null) page = 0;
        else page -= 1;

        return ResponseEntity.ok(service.getPosts(page));
    }

    @GetMapping("search")
    public ResponseEntity<List<PostDTO>> search(@RequestParam(required = false) String title, @RequestParam(required = false) String body) {
        return ResponseEntity.ok(service.search(title, body));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Validated(PostSaveDTO.OnCreate.class) PostSaveDTO dto) {
        return ResponseEntity.ok(service.createPost(dto));
    }

    @GetMapping("/{postid}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postid) {
        return ResponseEntity.ok(service.getPost(postid));
    }

    @PutMapping("/{postid}")
    public ResponseEntity<PostDTO> editPost(@PathVariable Long postid, @RequestBody @Validated(PostSaveDTO.OnUpdate.class) PostSaveDTO dto) {
        dto.setId(postid);
        return ResponseEntity.ok(service.editPost(dto));
    }

    @DeleteMapping("{postid}")
    public ResponseEntity<Void> removePost(@PathVariable Long postid) {
        service.removePost(postid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("foldername")
    public ResponseEntity<String> getFolderName() {
        return ResponseEntity.ok("게시판");
    }

    @GetMapping("{postid}/replies")
    ResponseEntity<List<ReplyDTO>> getReplies(@PathVariable Long postid) {
        return ResponseEntity.ok(service.getReplies(postid));
    }

    @PostMapping("{postid}/replies")
    ResponseEntity<ReplyDTO> createReply(@PathVariable Long postid, @RequestBody @Validated(ReplySaveDTO.OnCreate.class) ReplySaveDTO dto) {
        return ResponseEntity.ok(service.createReply(postid, dto));
    }

    @PutMapping("{postid}/replies/{replyid}")
    ResponseEntity<ReplyDTO> editReply(@PathVariable Long replyid, @RequestBody @Validated(ReplySaveDTO.OnUpdate.class) ReplySaveDTO dto, @PathVariable Long postid) {
        dto.setId(replyid);
        return ResponseEntity.ok(service.editReply(dto));
    }

    @DeleteMapping("{postid}/replies/{replyid}")
    ResponseEntity<Void> deleteReply(@PathVariable Long replyid, @PathVariable String postid) {
        service.removeReply(replyid);
        return ResponseEntity.noContent().build();
    }
}
