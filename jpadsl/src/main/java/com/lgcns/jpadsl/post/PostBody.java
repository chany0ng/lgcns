package com.lgcns.jpadsl.post;

import com.lgcns.jpadsl.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class PostBody extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Lob
    @Column(columnDefinition = "text")
    private String body;

    @OneToOne()
    @JoinColumn(
            name = "post",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_PostBody_post"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
