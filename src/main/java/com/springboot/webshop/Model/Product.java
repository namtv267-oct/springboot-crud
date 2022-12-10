package com.springboot.webshop.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @Column(name = "productId")
    private String productId;
    @Column(name = "categoryId")
    private String categoryId;
    @NotBlank(message = "Product's Name is invalid")
    @Size(min = 5)
    @Column(name="productName")
    private String productName;
    @PositiveOrZero
    @Column(name = "price")
    private int price;
    @NotBlank(message = "Product's Description is invalid")
    @Size(min = 2)
    @Column(name = "description")
    private String description;

    public Product() {
    }

    public Product(String productId, String categoryId, String productName, int price, String description) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
