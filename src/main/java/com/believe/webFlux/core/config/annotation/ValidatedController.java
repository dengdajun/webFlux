package com.believe.webFlux.core.config.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * <p> The validated controller. </p>
 *
 * @author Li Xingping
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
@RestController
@RequestMapping
public @interface ValidatedController {

    /**
     * Alias for {@link Validated#value}.
     */
    @AliasFor(annotation = Validated.class, attribute = "value")
    Class<?>[] validatedValue() default {};

    /**
     * Alias for {@link RestController#value}.
     */
    @AliasFor(annotation = RestController.class, attribute = "value")
    String controllerName() default "";

    /**
     * Alias for {@link RequestMapping#method}.
     */
    @AliasFor(annotation = RequestMapping.class)
    RequestMethod[] method() default {};

    /**
     * Alias for {@link RequestMapping#name}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    /**
     * Alias for {@link RequestMapping#value}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    /**
     * Alias for {@link RequestMapping#path}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    /**
     * Alias for {@link RequestMapping#params}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] params() default {};

    /**
     * Alias for {@link RequestMapping#headers}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    /**
     * Alias for {@link RequestMapping#consumes}.
     *
     * @since 4.3.5
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    /**
     * Alias for {@link RequestMapping#produces}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};

}
