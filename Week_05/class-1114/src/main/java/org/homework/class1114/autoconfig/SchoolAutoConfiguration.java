package org.homework.class1114.autoconfig;

import lombok.RequiredArgsConstructor;
import org.homework.class1114.domain.Klass;
import org.homework.class1114.domain.School;
import org.homework.class1114.domain.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@EnableConfigurationProperties(BeanConfig.class)
@Configuration
@RequiredArgsConstructor
public class SchoolAutoConfiguration  {


    private final BeanConfig beanConfig;

    @Bean
    @ConditionalOnClass(School.class)
    @ConditionalOnMissingBean(School.class)
    @ConditionalOnProperty(prefix = "student",name = "enabled", havingValue = "true", matchIfMissing = true)
    public School school(Klass klass, Student student) {
        return new School(klass, student);
    }

    @Bean
    @ConditionalOnClass(Klass.class)
    @ConditionalOnMissingBean(Klass.class)
    @ConditionalOnProperty(prefix = "klass", name = "enabled", havingValue = "true", matchIfMissing = true)
    public Klass klass(Student student) {
        return new Klass(Collections.singletonList(student));
    }

    @Bean
    @ConditionalOnClass(Student.class)
    @ConditionalOnMissingBean(Student.class)
    @ConditionalOnProperty(prefix = "student", name = "enabled", havingValue = "true", matchIfMissing = true)
    public Student student() {
        return new Student(beanConfig.getId(), beanConfig.getName());
    }


}
