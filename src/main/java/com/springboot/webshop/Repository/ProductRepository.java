package com.springboot.webshop.Repository;

import com.springboot.webshop.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,String> {
    Iterable<Product> findByCategoryId(String categoryId);
}
