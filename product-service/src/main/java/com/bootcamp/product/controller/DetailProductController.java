package com.bootcamp.product.controller;

import com.bootcamp.product.model.DetailProduct;
import com.bootcamp.product.service.DetailProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value="/detailproduct")
public class DetailProductController {
    @Autowired
    DetailProductService detailProductService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/List")
    public Flux<DetailProduct> AllDetProducts() {
        //List all detProducts
        LOGGER.info("List detProducts");
        return detailProductService.findAll();
    }

    @PostMapping(value = "/New")
    public Mono<DetailProduct> save(@RequestBody DetailProduct detailProduct){
        //Save DetProducts
        LOGGER.info("Save");
        return detailProductService.save(detailProduct);
    }

    @PutMapping(value = "/Update")
    public Mono<DetailProduct> update(@RequestBody DetailProduct detailProduct){
        //Update DetProducts
        LOGGER.info("Update");
        return detailProductService.update(detailProduct);
    }

    @PutMapping(value = "/Delete/{id}")
    public Mono<DetailProduct> Delete(@PathVariable("id")String id){
        //Logic Delete CatProduct
        LOGGER.info("Delete DetProduct");
        return detailProductService.DeleteDetProduct(id);
    }
}
