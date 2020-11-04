package com.production.server.service;

import com.production.server.exception.ProductNotFoundException;
import com.production.server.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductServiceImpl implements ProductService {

   static  List <Product> products=new ArrayList<>();
    static{
        products.add(new Product(1,"notebook",999.99));
        products.add(new Product(2,"cell phone",300.00));
        products.add(new Product(3,"kindle",150.99));
    }

    @Override
    public Product save(Product pr) {
        products.add(pr);
        return pr;
    }

    @Override
    public Product find(int id) {
        return products.stream().filter(pr->pr.getId()==id).findFirst().orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void delete(int id) {
        products.removeIf(pr -> pr.getId()==id);
    }

    @Override
    public void update(int id, Product newProd) {
        Product product = products.stream().filter(pr -> pr.getId() == id).findFirst().orElseThrow(ProductNotFoundException::new);
        product.setName(newProd.getName());
        product.setPrice(newProd.getPrice());

    }
}
