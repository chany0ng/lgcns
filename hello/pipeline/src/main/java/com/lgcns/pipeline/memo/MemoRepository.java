package com.lgcns.pipeline.memo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MemoRepository extends JpaRepository<Memo, Integer> {
    @Modifying
    @Transactional
    @Query("update Memo m set m.state = :nextState, m.statedAt = now() where m.state = :state and m.statedAt <= :timeToUp")
    int updateStateBatch(@Param("state") MemoState state,
            @Param("nextState") MemoState nextState,
            @Param("timeToUp") LocalDateTime timeToUp);
}
