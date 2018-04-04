package com.believe.webFlux.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 * @version at 2017/4/17 11:23
 * @since 1.0
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, exclude = {"operator"})
@ToString(callSuper = true, exclude = {"operator"})
public abstract class AuditingRecord<D extends AuditingDomain, S extends Enum<S>, O extends UserAccount>
  extends Record<D> {

    @NotNull
    @Enumerated(EnumType.STRING)
    private S status;

    @Enumerated(EnumType.STRING)
    private S beforeStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "operator_id")
    private UserAccount operator;

    protected void doRecord(D domain, S beforeStatus, S status, String remark, O operator) {
        super.doRecord(domain, remark);
        this.beforeStatus = beforeStatus;
        this.status = status;
        this.operator = operator;
    }

}
