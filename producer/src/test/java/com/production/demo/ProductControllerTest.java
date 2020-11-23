package com.production.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.production.server.controller.ProductController;
import com.production.server.model.Product;
import com.production.server.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


    @WebMvcTest(ProductController.class)
    @RunWith(SpringRunner.class)
    class ProductControllerTest {
        @MockBean
        private ProductService productService;

        @Autowired
        MockMvc mockMvc;

        @Autowired
        WebApplicationContext webApplicationContext;

        @Autowired
        ObjectMapper mapper;

        private Product product;

        private List<Product> productList;

        @BeforeEach
        void setUp() {

            mockMvc = MockMvcBuilders
                    .webAppContextSetup(this.webApplicationContext)
                    .build();

            product = new Product(1,"notebook",999.99);

            this.productList = new ArrayList<>();
            this.productList.add(new Product(1,"notebook",999.99));
            this.productList.add(new Product(2,"cell phone",300.00));
            this.productList.add(new Product(3,"kindle",150.99));

        }

        @Test
        void shouldFetchAllProductsTest() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3)));
        }

        @Test
        void getProductByIdTest() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/{id}",1))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("notebook"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(999.99));

        }

        @Test
        void saveProductTest() throws Exception {
            mockMvc.perform( MockMvcRequestBuilders
                    .put("/save/{id}", 2)
                    .content(mapper.writeValueAsString(new Product(4, "ipad", 800.00)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ipad"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(800.00));
        }

        @Test
        void deleteUserTest() throws Exception {
            mockMvc.perform( MockMvcRequestBuilders.delete("/delete/{id}", 1) )
                    .andExpect(status().isAccepted());
        }

        @Test
        void updateUserTest() throws Exception {


            mockMvc.perform( MockMvcRequestBuilders
                    .put("/update/{id}", 1)
                    .content(mapper.writeValueAsString(new Product(1, "playstation", 600.50)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("playstation"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(600.50));
        }
    }

