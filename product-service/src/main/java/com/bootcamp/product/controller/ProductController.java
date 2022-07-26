package com.bootcamp.product.controller;

import com.bootcamp.product.model.Product;
import com.bootcamp.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/list")
    public Flux<Product> AllClients() {
        //List all clients
        LOGGER.info("Hizo la peticion de listado");
        return productService.findAll();
    }

    @GetMapping(value = "/list/{cod}")
    public Mono<Product> Inquiry(@PathVariable("cod") String cod) {
        //List customers by code
        LOGGER.info("Hizo la peticion de listado");
        return productService.getProduct(cod);
    }

    @PostMapping(value = "/new")
    public Mono<Product> save(@RequestBody Product product){
        //Save Client
        LOGGER.info("Save");
        return productService.save(product);
    }

    @PutMapping(value = "/update")
    public Mono<Product> update(@RequestBody Product product){
        //Update Client
        LOGGER.info("Update Product");
        return productService.update(product);
    }

    @PutMapping(value = "/delete/{cod}")
    public Mono<Product> logicDelete(@PathVariable("cod")String cod){
        //Logic Delete
        LOGGER.info("Delete Product");
        return productService.logicDelete(cod);
    }

}
