package com.softwarepassion.sfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SfsMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfsMysqlApplication.class, args);
	}
}
