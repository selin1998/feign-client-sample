package com.client.controller;

import com.client.model.Product;
import com.client.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(consumerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable("id") int id) {
        return ResponseEntity.ok(consumerService.find(id));
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<Product> save( @RequestBody Product product) {
        return ResponseEntity.ok(consumerService.save(product));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Product product) {
        try {
            consumerService.update(id,product);
            return ResponseEntity.ok("success");
        } catch (Exception ex) {
            return ResponseEntity.ok("error");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
             consumerService.delete(id);
            return ResponseEntity.ok("success");
        } catch (Exception ex) {
            return ResponseEntity.ok("error");
        }
    }
}
