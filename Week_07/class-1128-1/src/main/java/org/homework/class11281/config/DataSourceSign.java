package org.homework.class11281.config;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSign {
    ContextConst.DataSourceType value() default ContextConst.DataSourceType.MASTER;
}
