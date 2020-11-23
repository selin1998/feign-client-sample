package com.production.server.data;

import com.production.server.model.Product;
import com.production.server.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Initializer {

    private final ProductService productService;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            productService.save(new Product(1,"notebook",999.99));
            productService.save(new Product(2,"cell phone",300.00));
            productService.save(new Product(3,"kindle",150.99));

        };
    }
}
