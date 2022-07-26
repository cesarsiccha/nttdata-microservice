package com.bootcamp.product.controller;

import com.bootcamp.product.model.CategoryProduct;
import com.bootcamp.product.service.CategoryProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value="/categoryproduct")
public class CategoryProductController {
    @Autowired
    CategoryProductService categoryProductService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/List")
    public Flux<CategoryProduct> AllCatProducts() {
        //List all catProducts
        LOGGER.info("List CatProducts");
        return categoryProductService.findAll();
    }

    @PostMapping(value = "/New")
    public Mono<CategoryProduct> save(@RequestBody CategoryProduct categoryProduct){
        //Save CatProducts
        LOGGER.info("Save");
        return categoryProductService.save(categoryProduct);
    }

    @PutMapping(value = "/Update")
    public Mono<CategoryProduct> update(@RequestBody CategoryProduct categoryProduct){
        //Update CatProducts
        LOGGER.info("Update");
        return categoryProductService.update(categoryProduct);
    }

    @PutMapping(value = "/Delete/{id}")
    public Mono<CategoryProduct> Delete(@PathVariable("id")String id){
        //Logic Delete CatProduct
        LOGGER.info("Delete CatProduct");
        return categoryProductService.DeleteCatProduct(id);
    }
}
