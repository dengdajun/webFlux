package com.believe.webFlux.core.config.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

/**
 * <p> The validated service . </p>
 *
 * @author Li Xingping
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidatedService
@Transactional
public @interface TransactionalService {

    /**
     * Alias for {@link Validated#value}.
     */
    @AliasFor(annotation = ValidatedService.class, attribute = "validatedValue")
    Class<?>[] validatedValue() default {};

    /**
     * Alias for {@link Service#value}.
     */
    @AliasFor(annotation = ValidatedService.class)
    String value() default "";

    /**
     * Alias for {@link Transactional#value}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "value")
    String transactionManager() default "";

    /**
     * Alias for {@link Transactional#propagation}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "propagation")
    Propagation propagation() default Propagation.REQUIRED;

    /**
     * Alias for {@link Transactional#isolation}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "isolation")
    Isolation isolation() default Isolation.DEFAULT;

    /**
     * Alias for {@link Transactional#timeout}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "timeout")
    int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;

    /**
     * Alias for {@link Transactional#readOnly}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "readOnly")
    boolean readOnly() default false;

    /**
     * Alias for {@link Transactional#rollbackFor}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "rollbackFor")
    Class<? extends Throwable>[] rollbackFor() default {};

    /**
     * /**
     * Alias for {@link Transactional#rollbackForClassName}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "rollbackForClassName")
    String[] rollbackForClassName() default {};

    /**
     * Alias for {@link Transactional#noRollbackFor}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "noRollbackFor")
    Class<? extends Throwable>[] noRollbackFor() default {};

    /**
     * Alias for {@link Transactional#noRollbackForClassName}.
     */
    @AliasFor(annotation = Transactional.class, attribute = "noRollbackForClassName")
    String[] noRollbackForClassName() default {};

}
