package com.lgcns.jpadsl.post;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
class PostRepositoryTest {
    private static final String title = "test title..";
    private static final String writer = "hong";

    @Autowired
    private PostRepository repository;

    private Long newId;
    private long orgCount;

    @Test
    @Order(1)
    void createTest() {
        this.orgCount = repository.count();
        Post post = Post.builder()
                .title(title)
                .writer(writer)
                .body("body")
                .build();

        Post newer = repository.save(post);
        this.newId = newer.getId();
        assertNotNull(newer.getId());
        assertEquals(writer, newer.getWriter());
        assertEquals(title, newer.getTitle());
        assertEquals(repository.count(), orgCount + 1);
    }

    @Test
    @Order(2)
    void readTest() {
        Post post = repository.findById(this.newId).orElseThrow();
        assertEquals(writer, post.getWriter());
        assertEquals(title, post.getTitle());
    }

    @Test
    @Order(3)
    void updateTest() {
        Post post = repository.findById(this.newId).orElseThrow();
        post.setTitle(title + "-new");
        post.setWriter(writer + "-new");
        repository.save(post);
    }

    @Test
    @Order(4)
    void readAfterUpdateTest() {
        Post post = repository.findById(this.newId).orElseThrow();
        assertEquals(title + "-new", post.getTitle());
        assertEquals(writer + "-new", post.getWriter());
    }

    @Test
    @Order(5)
    void deleteTest() {
        Post post = repository.findById(this.newId).orElseThrow();
        repository.delete(post);
        assertEquals(orgCount, repository.count());
    }

    @Test
    void createMany() {
        if (repository.count() >= 100) return;
        
        List<Post> list = LongStream.rangeClosed(1, 100).mapToObj(l -> {
                    Post post = Post.builder()
                            .title("title" + l)
                            .writer("writer" + l)
                            .body("body" + l)
                            .build();
                    return post;
                }
        ).toList();

        repository.saveAll(list);
    }
}
