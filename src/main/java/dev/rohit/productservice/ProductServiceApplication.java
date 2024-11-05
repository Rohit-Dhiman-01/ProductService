package dev.rohit.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.rohit.productservice.repositories.CategoryRepository;
import dev.rohit.productservice.repositories.ProductRepository;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	public ProductServiceApplication(
									 ProductRepository productRepository,
									 CategoryRepository categoryRepository
									){
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
