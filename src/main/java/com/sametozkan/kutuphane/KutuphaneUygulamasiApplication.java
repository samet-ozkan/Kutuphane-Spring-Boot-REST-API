package com.sametozkan.kutuphane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class KutuphaneUygulamasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KutuphaneUygulamasiApplication.class, args);
	}

}
