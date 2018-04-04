package com.believe.webFlux.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, exclude = {"lastOperator", "records"})
@ToString(callSuper = true, exclude = {"lastOperator", "records"})
public abstract class AuditingDomain<S extends Enum<S>,
  R extends AuditingRecord<D, S, UserAccount>, D extends AuditingDomain> extends Domain {

    @NotNull
    @Enumerated(EnumType.STRING)
    protected S status;

    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "last_operator_id")
    protected UserAccount lastOperator;

    @JsonIgnore
    @OrderBy("createdDate desc ")
    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
    protected List<R> records = Lists.newArrayList();

    @SuppressWarnings("unchecked")
    protected R changeState(S beforeStatus, S status, String remark, UserAccount operator) {
        R record = newRecord();
        record.doRecord((D) this, beforeStatus, status, remark, operator);
        this.records.add(record);
        this.status = status;
        return record;
    }

    protected abstract R newRecord();

    public List<R> getRecords() {
        return Collections.unmodifiableList(this.records);
    }

}
