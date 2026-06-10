package com.lgcns.pipeline.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false, length = 30)
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    @Builder.Default
    @ToString.Exclude
    private List<UserRole> roles = new ArrayList<>();

    public User addRole(UserRole role) {
        if (this.roles == null)
            this.roles = new ArrayList<>();

        this.roles.add(role);
        return this;
    }

    public void clearRoles() {
        if (this.roles != null) this.roles.clear();
    }
}
