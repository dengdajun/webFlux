package com.believe.webFlux.core.model;

import com.believe.webFlux.core.listener.RecordListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, exclude = {"domain"})
@ToString(callSuper = true, exclude = {"domain"})
@EntityListeners(value = {RecordListener.class})
public abstract class Record<D extends Domain> extends PersistentDomain<String> {

    @Column
    protected String remark;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "domain_id")
    protected D domain;

    public void doRecord(D domain, String remark) {
        this.domain = domain;
        this.remark = remark;
    }

}
