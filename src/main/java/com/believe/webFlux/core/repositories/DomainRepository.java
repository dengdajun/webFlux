package com.believe.webFlux.core.repositories;

import com.believe.webFlux.core.exception.WebApiException;
import com.believe.webFlux.core.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Administrator on 2016/3/11.
 */
@NoRepositoryBean
public interface DomainRepository<T extends Domain> extends JpaRepository<T, String> {

    default T byId(String id) {
        return this.findById(id).orElseThrow(() -> new WebApiException(String.format("记录 %s 不存在", id)));
    }
}
