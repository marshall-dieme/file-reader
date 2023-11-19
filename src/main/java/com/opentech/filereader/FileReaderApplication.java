package com.opentech.filereader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.opentech.filereader.application")
public class FileReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileReaderApplication.class, args);
	}

}
