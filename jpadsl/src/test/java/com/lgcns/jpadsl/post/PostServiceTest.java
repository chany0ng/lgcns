package com.lgcns.jpadsl.post;

import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostServiceTest {
    private static final Long postId = 1L;
    private static final Post post = Post.builder()
            .id(postId)
            .title("title")
            .writer("writer")
            .body("body")
            .build();

    @MockitoBean
    PostRepository repository;

    @Autowired
    PostService service;

    @BeforeEach
    void setup() {
        BDDMockito.given(repository.findById(postId))
                .willReturn(Optional.ofNullable(post));
    }

    @Test
    void selectOneTest() {
        PostDTO dto = service.getPost(postId);
//        Assertions.assertEquals(postId, dto.id());
        assertEquals(postId, dto.id());
        assertEquals(post.getTitle(), dto.title());
    }
}
