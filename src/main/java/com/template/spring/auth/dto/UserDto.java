package com.template.spring.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.template.spring.core.config.Config;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class UserDto implements Serializable {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATE_TIME_FULL)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATE_TIME_FULL)
    private Date updatedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATE_TIME_FULL)
    private Date deletedAt;

    @Email(message = "Must be a valid e-mail")
    @NotEmpty(message = "E-mail is required")
    private String email;

    @NotEmpty(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only alphanumeric characters can be used for username")
    @Size(max = 20, message = "Maximum 14 characters for username")
    private String username;
}
