package com.believe.webFlux.core.model;

import com.believe.webFlux.core.listener.DomainListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(value = {DomainListener.class})
public abstract class Domain extends PersistentDomain<String> {

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

}
