package com.production.demo;

import com.client.controller.ConsumerController;
import com.client.model.Product;
import com.client.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.ContentRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerController.class)
@RunWith(SpringRunner.class)
public class ConsumerControllerTest {

        @MockBean
        private ConsumerService consumerService;

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

            product = Product.builder()
                    .id(1)
                    .name("notebook")
                    .price(1200.99)
                    .build();

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
