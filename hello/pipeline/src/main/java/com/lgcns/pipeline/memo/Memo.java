package com.lgcns.pipeline.memo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mno;

    @Column(nullable = false, length = 200)
    private String memoText;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PAYED'")
    private MemoState state;

    @Column(columnDefinition
            = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime statedAt;
}
