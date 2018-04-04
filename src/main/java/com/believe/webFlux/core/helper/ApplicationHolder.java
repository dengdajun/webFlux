package com.believe.webFlux.core.helper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p> The describe </p>
 *
 * @author Li Xingping
 */
@Component
public class ApplicationHolder implements EnvironmentAware, InitializingBean {
    private Environment environment;
    private static List<String> profiles;
    private AtomicBoolean initialized = new AtomicBoolean(false);

    public static boolean isDevOrLocal() {
        return !CollectionUtils.isEmpty(profiles) && (
          profiles.contains("dev") || profiles.contains("local")
        );
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (initialized.compareAndSet(false, true)) {
            profiles = Arrays.asList(environment.getActiveProfiles());
        }
    }
}
