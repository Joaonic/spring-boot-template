package com.template.spring.auth.model;

import com.template.spring.auth.enums.UserLevel;
import com.template.spring.core.config.Config;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = Config.AUTH_SCHEMA, name = "role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(schema = Config.AUTH_SCHEMA,
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    @ToString.Exclude
    private Set<Privilege> privileges = new HashSet<>();

    @Transient
    private UserLevel userLevel;

    public UserLevel getUserLevel() {
        return UserLevel.valueOf(name);
    }
}
