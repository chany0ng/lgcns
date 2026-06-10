package com.lgcns.jpadsl.user;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmailForUpdate(@Param("email") String email);

    // select * from User where nickname = ?
    List<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);
    
    Optional<User> findFirstByNickname(String nickname);

    long countByBloodType(BloodType bloodType);
}
