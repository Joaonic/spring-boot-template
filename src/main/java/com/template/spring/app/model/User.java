package com.template.spring.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.template.spring.core.model.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "app", indexes = {
        @Index(name = "un_user_username", columnList = "username", unique = true),
        @Index(name = "un_user_email", columnList = "email", unique = true)
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SequenceGenerator(sequenceName = "app.user_id_seq", allocationSize = 1, name = "default_seq_gen")
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

    @Column(name = "password", length = 128)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

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