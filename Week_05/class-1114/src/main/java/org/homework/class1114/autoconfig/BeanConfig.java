package org.homework.class1114.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "student")
@Getter
@Setter
public class BeanConfig {
    private int id;
    private String name;
}
