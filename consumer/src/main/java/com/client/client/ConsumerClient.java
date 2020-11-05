package com.client.client;

import com.client.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "consumerClient", url = "http://localhost:9000")
public interface ConsumerClient {

    @GetMapping("/")
    List<Product> findAll();

    @GetMapping("/{id}")
    Product find(@PathVariable("id") int id);

    @PostMapping("/save")
    Product save(@RequestBody Product Product);

    @PostMapping("/delete/{id}")
    void delete(@PathVariable("id") int id);

    @PostMapping("/update/{id}")
    void update(@PathVariable("id") int id, @RequestBody Product Product);
}
