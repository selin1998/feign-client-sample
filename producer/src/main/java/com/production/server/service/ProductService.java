package com.production.server.service;

import com.production.server.model.Product;

import java.util.List;

public interface ProductService {



    Product find(int id);

    List<Product> findAll();

    Product save(Product pr);

    void delete(int id);

    void update(int id, Product pr);
}
