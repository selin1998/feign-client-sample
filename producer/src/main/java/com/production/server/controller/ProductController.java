package com.production.server.controller;

import com.production.server.model.Product;
import com.production.server.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product find(@PathVariable("id") int id){
        return productService.find(id);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product Product){
        return productService.save(Product);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        productService.delete(id);
    }

    @PostMapping("/update/{id}")
    public void update(int id, @RequestBody Product Product){
        productService.update(id, Product);
    }

}
