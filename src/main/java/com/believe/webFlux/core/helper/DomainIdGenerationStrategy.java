package com.believe.webFlux.core.helper;

import com.eaio.uuid.UUID;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerationStrategy;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
public class DomainIdGenerationStrategy implements UUIDGenerationStrategy {
    @Override
    public int getGeneratedVersion() {
        return 1;
    }

    @Override
    public java.util.UUID generateUUID(SharedSessionContractImplementor session) {
        return java.util.UUID.fromString(
          new UUID().toString()
        );
    }
}
