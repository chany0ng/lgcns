package com.lgcns.jpadsl.post;

import com.lgcns.jpadsl.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 31)
    private String writer;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private PostBody body;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

    @ManyToMany(mappedBy = "posts")
    @Builder.Default
    @ToString.Exclude
    private List<Hashtag> hashtags = new ArrayList<>();

    public void setBody(PostBody body) {
        this.body = body;
        if (body != null) {
            body.setPost(this);
        }
    }
}
