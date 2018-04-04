package com.believe.webFlux.core.repositories;

import com.believe.webFlux.core.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
@NoRepositoryBean
public interface RecordRepository<T extends Record> extends JpaRepository<T, String> {

    Long countByDomainId(String domainId);

    List<T> findByDomainIdOrderByCreatedDateDesc(String domainId);

}
