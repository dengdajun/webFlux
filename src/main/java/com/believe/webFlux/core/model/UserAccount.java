package com.believe.webFlux.core.model;

import com.believe.webFlux.core.config.annotation.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_account", uniqueConstraints = {
  @UniqueConstraint(name = "userName", columnNames = "user_name")
})
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserAccount extends Domain {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "{Validated.UserAccount.userName.Pattern}")
    @ErrorCode("Validated.UserAccount.userName.Pattern")
    @Column(name = "user_name", nullable = false, unique = true)
    protected String userName;

    @JsonIgnore
    @Column
    protected String password;

    @Column
    private boolean locked;

}
