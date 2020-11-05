package com.client.service;

import com.client.client.ConsumerClient;
import com.client.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ConsumerService {
    List<Product> findAll();

    Product find(int id);

    Product save(Product product);

    void delete(int id);

    void update(int searchId,  Product product);

    
}
