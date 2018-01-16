package com.anagram.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.DispatcherServlet;



@Configuration
@ComponentScan("com.anagram")
@SpringBootApplication
public class Application {
	
	@Value("${api.config.context}") 
	private String contextUrl;
	
	public static final List<String> parsedFileArray = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		Application application = new Application();
		application.loadFileToMemory("wordlist.txt");
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), contextUrl);
		registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
		return registration;
	}


	public void loadFileToMemory(String fileName) throws IOException {
		
		
		try {
			 Resource resource = new ClassPathResource(fileName);
			 Path pathFile = Paths.get(resource.getURI());
			 
			 Files.readAllLines(pathFile)
				    .parallelStream()
				    .map(line -> line.split("[\\s*]"))
				    .flatMap(Arrays::stream)
				    .filter(line -> !line.contains("'"))
				    .forEach(line -> parsedFileArray.addAll(Arrays.asList(line)));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
	}
}
