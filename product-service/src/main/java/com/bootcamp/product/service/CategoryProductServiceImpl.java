package com.bootcamp.product.service;

import com.bootcamp.product.model.CategoryProduct;
import com.bootcamp.product.model.Product;
import com.bootcamp.product.repository.CategoryProductRepository;
import com.bootcamp.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryProductServiceImpl implements CategoryProductService{
    @Autowired
    private CategoryProductRepository categoryProductRepository;


    @Override
    public Flux<CategoryProduct> findAll() {
        return categoryProductRepository.findAll();
    }

    @Override
    public Mono<CategoryProduct> save(CategoryProduct categoryProduct) {
        return categoryProductRepository.save(categoryProduct);
    }

    @Override
    public Mono<CategoryProduct> update(CategoryProduct categoryProduct) {
        Mono<CategoryProduct> categoryProductMono = categoryProductRepository.findAll().filter(x -> x.getIdCategoryProduct().equals(categoryProduct.getIdCategoryProduct())).next();

        CategoryProduct categoryProductbd = categoryProductMono.block();

        categoryProductbd.setDescription(categoryProduct.getDescription());
        categoryProductbd.setState(categoryProduct.getState());

        return categoryProductRepository.save(categoryProductbd);
    }

    @Override
    public Mono<CategoryProduct> DeleteCatProduct(String id) {

        Mono<CategoryProduct> categoryProductMono = categoryProductRepository.findAll().filter(x -> x.getIdCategoryProduct().equals(id)).next();
        CategoryProduct categoryProduct = categoryProductMono.block();
        categoryProduct.setState(0);

        return categoryProductRepository.save(categoryProduct);
    }
}
