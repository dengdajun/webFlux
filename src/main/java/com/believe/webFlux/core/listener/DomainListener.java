package com.believe.webFlux.core.listener;

import com.believe.webFlux.core.helper.TimeHelper;
import com.believe.webFlux.core.model.Domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 */
public class DomainListener {

    @PrePersist
    public void persist(final Domain entity) {
        if (null == entity.getCreatedDate()) {
            Instant now = TimeHelper.now();
            entity.setCreatedDate(now);
            entity.setLastModifiedDate(now);
        }
    }

    @PreUpdate
    public void update(final Domain entity) {
        entity.setLastModifiedDate(TimeHelper.now());
    }

}
