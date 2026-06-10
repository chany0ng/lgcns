package com.lgcns.jpadsl.user;

import com.lgcns.jpadsl.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "uniq_User_email",
                columnNames = {"email"}
        )
})
@DynamicInsert
@Getter
@Setter
@ToString(exclude = {"passwd"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(length = 31, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    private String passwd;

//    @Column(length = 12)
//    private String telno;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'A'")
    private BloodType bloodType;

    @Transient
    private String creditCard;
}
