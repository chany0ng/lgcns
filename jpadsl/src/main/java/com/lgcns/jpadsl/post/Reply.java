package com.lgcns.jpadsl.post;

import com.lgcns.jpadsl.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(length = 500, nullable = false)
    private String reply;

    @Column(nullable = false, length = 31)
    private String replier;

    @ManyToOne()
    @JoinColumn(
            name = "post",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_Reply_post"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Post post;
}
