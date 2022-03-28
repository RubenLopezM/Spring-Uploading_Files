package com.example.uploadingfiles;

import com.example.uploadingfiles.application.StorageProperties;
import com.example.uploadingfiles.application.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UploadingFilesApplication {

	public static void main(String[] args) {

		SpringApplication.run(UploadingFilesApplication.class, args);
	}

	@Autowired
	StorageProperties properties;

	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args) -> {
			if (args.length==0){
			storageService.init(null);
		} else {
			storageService.init(args[0]);
			properties.setLocation(args[0]);
			}
		};
	}

}
