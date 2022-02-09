package com.gisnet.egisfil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gisnet.egisfil"})
public class DisplayApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DisplayApplication.class, args);
	}
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
            return builder.sources(DisplayApplication.class);
        }

}
