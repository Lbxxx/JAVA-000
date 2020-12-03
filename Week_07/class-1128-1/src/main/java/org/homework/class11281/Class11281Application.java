package org.homework.class11281;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan({"org.homework.class11281.mapper"})
public class Class11281Application {

	public static void main(String[] args) {
		SpringApplication.run(Class11281Application.class, args);
	}

}
