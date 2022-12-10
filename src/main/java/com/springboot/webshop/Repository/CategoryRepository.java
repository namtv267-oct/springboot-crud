package com.springboot.webshop.Repository;

import com.springboot.webshop.Model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,String> {
    Iterable<Category> findAll();

}
