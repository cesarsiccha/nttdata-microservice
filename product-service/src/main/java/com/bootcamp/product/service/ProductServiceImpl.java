package com.bootcamp.product.service;

import com.bootcamp.product.model.Product;
import com.bootcamp.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> getProduct(String idProduct) {
        Mono<Product> productMono = productRepository.findAll().filter(x -> x.getIdProduct().equals(idProduct)).next();

        return productMono;
    }

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        Mono<Product> ProductMono = productRepository.findAll().filter(x -> x.getIdProduct().equals(product.getIdProduct())).next();

        Product productbd = ProductMono.block();

        productbd.setName(product.getName());
        productbd.setDescription(product.getDescription());
        productbd.setCategoryProduct(product.getCategoryProduct());
        productbd.setState(product.getState());

        return productRepository.save(productbd);
    }

    @Override
    public Mono<Product> logicDelete(String idProduct) {

        Mono<Product> clientMono = productRepository.findAll().filter(x -> x.getIdProduct().equals(idProduct)).next();
        Product product = clientMono.block();
        product.setState(0);

        return productRepository.save(product);
    }
}
