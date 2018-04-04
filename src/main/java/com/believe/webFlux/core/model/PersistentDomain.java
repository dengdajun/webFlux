package com.believe.webFlux.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * About uuid {@literal http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/mapping.html#d0e5294}
 */
@MappedSuperclass
@Data
public class PersistentDomain<ID extends Serializable> implements Serializable {

    @JsonProperty("id")
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2",
      parameters = {
        @Parameter(
          name = "uuid_gen_strategy_class",
          value = "com.believe.webFlux.core.helper.DomainIdGenerationStrategy"
        )
      }
    )
    @Column(name = "id", updatable = false)
    private ID id;

    @Column(nullable = false, updatable = false, name = "created_date")
    private Instant createdDate;

}
