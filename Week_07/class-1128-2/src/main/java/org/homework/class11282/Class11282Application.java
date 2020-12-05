package org.homework.class11282;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"org.homework.class11282.mapper"})
public class Class11282Application {

	public static void main(String[] args) {
		SpringApplication.run(Class11282Application.class, args);
	}

}
