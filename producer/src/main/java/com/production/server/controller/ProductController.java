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

    @GetMapping("/all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/get")
    public Product find(int id){
        return productService.find(id);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product Product){
        return productService.save(Product);
    }

    @PostMapping("/delete")
    public void delete(int id){
        productService.delete(id);
    }

    @PostMapping("/update")
    public void update(int searchId, @RequestBody Product Product){
        productService.update(searchId, Product);
    }

}
