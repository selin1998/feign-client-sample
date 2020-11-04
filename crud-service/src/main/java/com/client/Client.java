package com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "products")
public interface Client {

    @GetMapping("/")
    List<Product> getProducts();

    @GetMapping("/{id}")
    Product get(@PathVariable("id") int id);

    @PostMapping("/save")
    Product save(@RequestBody Product Product);

    @PostMapping("/delete/{id}")
    void delete(@PathVariable("id") int id);

    @PostMapping("/update/{id}")
    void update(@PathVariable("id") int id, @RequestBody Product Product);
}
