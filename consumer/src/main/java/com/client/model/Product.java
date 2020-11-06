package com.client.model;

import lombok.*;
import org.springframework.data.annotation.Id;


import java.io.Serializable;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String name;

    private Double price;
}
