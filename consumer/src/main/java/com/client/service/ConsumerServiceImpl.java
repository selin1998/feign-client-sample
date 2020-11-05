package com.client.service;

import com.client.client.ConsumerClient;
import com.client.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerClient consumerClient;

    @Override
    public List<Product> findAll() {
        return consumerClient.findAll();
    }

    @Override
    public Product find(int id) {
        return consumerClient.find(id);
    }

    @Override
    public Product save(Product product) {
        return consumerClient.save(product);
    }

    @Override
    public void delete(int id) {
        consumerClient.delete(id);
    }

    @Override
    public void update(int id, Product product) {
        consumerClient.update(id, product);
    }
}
