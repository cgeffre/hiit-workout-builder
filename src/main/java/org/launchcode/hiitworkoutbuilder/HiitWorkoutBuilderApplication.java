package org.launchcode.hiitworkoutbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HiitWorkoutBuilderApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HiitWorkoutBuilderApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(HiitWorkoutBuilderApplication.class, args);
	}

}
