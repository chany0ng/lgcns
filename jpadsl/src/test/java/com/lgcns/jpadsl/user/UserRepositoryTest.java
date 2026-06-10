package com.lgcns.jpadsl.user;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
class UserRepositoryTest {
    private static final String email = "hong@gmail.com";
    private static final String nickname = "kildong";

    @Autowired
    private UserRepository repository;

    @Test
    @Order(1)
    void createTest() {
        long orgCount = repository.count();

        User sampleUser = User.builder()
                .nickname(nickname)
                .email(email)
                .bloodType(BloodType.A)
                .build();

        User savedUser = repository.save(sampleUser);

        assertNotNull(savedUser.getId());
        assertEquals(repository.count(), orgCount + 1);
        assertEquals(sampleUser.getNickname(), savedUser.getNickname());
        assertEquals(sampleUser.getBloodType(), savedUser.getBloodType());
    }

    @Test
    @Order(2)
    void readTest() {
        User user = repository.findFirstByNickname(nickname).orElseThrow();
        assertEquals(nickname, user.getNickname());
        assertEquals(email, user.getEmail());
    }

    @Test
    @Order(3)
    void updateTest() {
        User user = repository.findByEmailForUpdate(email).orElseThrow();
        user.setNickname(nickname + "New");
        repository.save(user);
    }

    @Test
    @Order(4)
    void readAfterUpdateTest() {
        User user = repository.findFirstByNickname(nickname + "New").orElseThrow();
        assertEquals(email, user.getEmail());
    }

    @Test
    @Order(5)
    void deleteTest() {
        User user = repository.findByEmailForUpdate(email).orElseThrow();
        repository.delete(user);
    }

    @Test
    @Order(6)
    void readAfterDeleteTest() {
        Optional<User> byEmail = repository.findByEmail(email);
        if (byEmail.isPresent()) throw new IllegalArgumentException("Not deleted!");
    }
}
