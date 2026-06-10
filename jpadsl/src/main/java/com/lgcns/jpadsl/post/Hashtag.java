package com.lgcns.jpadsl.post;

import com.lgcns.jpadsl.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Hashtag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String tag;

    @ManyToMany()
    @JoinTable(
            joinColumns = @JoinColumn(name = "hashtag",
                    foreignKey = @ForeignKey(name = "fk_HashtagPost_hashtag")),
            inverseJoinColumns = @JoinColumn(name = "post",
                    foreignKey = @ForeignKey(name = "fk_HashtagPost_post",
                            foreignKeyDefinition = "foreign key(post) references Post(id) on delete cascade"))
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

    public Hashtag(String tag) {
        this.tag = tag;
    }

    public void addPost(Post post) {
        if (posts == null)
            this.posts = new ArrayList<>();

        this.posts.add(post);
    }
}
