package com.production.server.model;

import lombok.*;
import org.springframework.data.annotation.Id;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Double price;


}
