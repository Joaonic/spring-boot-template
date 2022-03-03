package com.template.spring.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.template.spring.core.config.Config;
import com.template.spring.core.model.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SequenceGenerator(sequenceName = Config.AUTH_SCHEMA + ".user_id_seq", allocationSize = 1, name = "user_seq_gen")
@Entity
@Table(name = "user", schema = Config.AUTH_SCHEMA, indexes = {
        @Index(name = "un_user_username", columnList = "username", unique = true),
        @Index(name = "un_user_email", columnList = "email", unique = true)
})
public class User extends BaseModel {

    @Column(name = "email", length = 50, nullable = false)
    @Email(message = "Must be a valid e-mail")
    @NotEmpty(message = "E-mail is required")
    private String email;

    @Column(name = "username", length = 20, nullable = false)
    @NotEmpty(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only alphanumeric characters can be used for username")
    @Size(max = 20, message = "Maximum 14 characters for username")
    private String username;

    @Column(name = "image")
    private String image;

    @Column(name = "password", length = 128)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = Config.AUTH_SCHEMA, name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    @Builder.Default
    private Set<Role> roles = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}