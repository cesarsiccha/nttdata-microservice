package com.bootcamp.product.service;

import com.bootcamp.product.model.CategoryProduct;
import com.bootcamp.product.model.DetailProduct;
import com.bootcamp.product.repository.CategoryProductRepository;
import com.bootcamp.product.repository.DetailProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class DetailProductServiceImpl implements DetailProductService{

    @Autowired
    private DetailProductRepository detailProductRepository;

    @Override
    public Flux<DetailProduct> findAll() {
        return detailProductRepository.findAll();
    }

    @Override
    public Mono<DetailProduct> save(DetailProduct detailProduct) {
        return detailProductRepository.save(detailProduct);
    }

    @Override
    public Mono<DetailProduct> update(DetailProduct detailProduct) {
        Mono<DetailProduct> categoryProductMono = detailProductRepository.findAll().filter(x -> x.getIdDetailProduct().equals(detailProduct.getIdDetailProduct())).next();

        DetailProduct detailProductbd = categoryProductMono.block();

        detailProductbd.setProduct(detailProduct.getProduct());
        detailProductbd.setClient(detailProduct.getClient());
        detailProductbd.setAccount(detailProduct.getAccount());
        detailProductbd.setAmount(detailProduct.getAmount());
        detailProductbd.setState(detailProduct.getState());

        return detailProductRepository.save(detailProductbd);
    }

    @Override
    public Mono<DetailProduct> DeleteDetProduct(String id) {
        Mono<DetailProduct> detailProductMono = detailProductRepository.findAll().filter(x -> x.getIdDetailProduct().equals(id)).next();
        DetailProduct detailProduct = detailProductMono.block();
        detailProduct.setState(0);

        return detailProductRepository.save(detailProduct);
    }
}
