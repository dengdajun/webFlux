package com.believe.webFlux.users.domain;

import com.believe.webFlux.core.model.UserAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * <p> The user entity. </p>
 *
 * @author Li Xingping
 */

@Data
@Entity
@Table(name = "user")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends UserAccount {

    @NotBlank
    @Column(name = "real_name")
    public String realName;

}
