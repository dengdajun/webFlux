package com.believe.webFlux.core.listener;

import com.believe.webFlux.core.helper.TimeHelper;
import com.believe.webFlux.core.model.Record;

import javax.persistence.PrePersist;

/**
 * <p> The describe </p>
 *
 * @author lixingping
 */
public class RecordListener {

    @PrePersist
    public void persist(final Record record) {
        if (null == record.getCreatedDate()) {
            record.setCreatedDate(TimeHelper.now());
        }
    }

}
