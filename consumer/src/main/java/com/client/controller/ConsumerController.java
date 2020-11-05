package com.client.controller;

import com.client.model.Product;
import com.client.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping("all")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(consumerService.findAll());
    }

    @GetMapping("get")
    public ResponseEntity<Product> find(int id) {
        return ResponseEntity.ok(consumerService.find(id));
    }

    @PostMapping("save")
    public ResponseEntity<Product> save(Product product) {
        return ResponseEntity.ok(consumerService.save(product));
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(int id) {
        try {
             consumerService.delete(id);
            return ResponseEntity.ok("success");
        } catch (Exception ex) {
            return ResponseEntity.ok("");
        }
    }
}
