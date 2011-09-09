package com.parsek.test.data;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A qualifier used to differentiate between multiple data repositories.
 * 
 * If you only have 1 EntityManager, this annotation is optional
 */
@Qualifier
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberRepository {
    /* class body intentionally left blank */
}
