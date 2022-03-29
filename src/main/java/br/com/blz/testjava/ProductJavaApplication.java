package br.com.blz.testjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = ProductJavaApplication.class)
public class ProductJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductJavaApplication.class, args);
	}
}
