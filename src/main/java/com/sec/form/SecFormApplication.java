package com.sec.form;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.sec.form.mapper")
@SpringBootApplication
public class SecFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecFormApplication.class, args);
	}

}
