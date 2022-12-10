package com.springboot.webshop.Controller;

import com.springboot.webshop.Model.Category;
import com.springboot.webshop.Model.Product;
import com.springboot.webshop.Repository.CategoryRepository;
import com.springboot.webshop.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public String getAllProducts(ModelMap modelMap){
        Iterable<Product> products = productRepository.findAll();
        modelMap.addAttribute("products",products);
        return "production";
    }
    @GetMapping("/getProductsByCategoryId/{categoryId}")
    public String getProductsByCategoryId(@PathVariable String categoryId, ModelMap modelMap) {
        Iterable<Product> products = productRepository.findByCategoryId(categoryId);
        modelMap.addAttribute("products", products);
        return "production";
    }

    @GetMapping("/changeCategory/{productId}")
    public String changeCategory(ModelMap modelMap, @PathVariable String productId) {
        Iterable<Category> categories = categoryRepository.findAll();
        Optional<Product> product = productRepository.findById(productId);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("product", product.get());
        return "updateProduct";
    }

    @PostMapping("/updateProduct/{productId}")
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                @PathVariable String productId) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("categories", categoryRepository.findAll());
            return "insertProduct";

            //return "redirect:/products/changeCategory/"+productId;
        }
        try {
            if (productRepository.findById(productId).isPresent()) {
                Product foundProduct = productRepository.findById(product.getProductId()).get();
                if (!product.getProductName().trim().isEmpty()) {
                    foundProduct.setProductName(product.getProductName());
                }
                if (!product.getCategoryId().isEmpty()) {
                    foundProduct.setCategoryId(product.getCategoryId());
                }
                if (!product.getDescription().trim().isEmpty()) {
                    foundProduct.setDescription(product.getDescription());
                }
                if (product.getPrice() > 0) {
                    foundProduct.setPrice(product.getPrice());
                }
                productRepository.save(foundProduct);
            }
            return "redirect:/products/getProductsByCategoryId/" + product.getCategoryId();
        } catch (Exception ex) {
            modelMap.addAttribute("error",ex.toString());
            return "insertProduct";
        }

    }

    @RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
    public String insertProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "insertProduct";
    }

    @RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
    public String insertProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insertProduct";
        }
        try{
            product.setProductId(UUID.randomUUID().toString());
            productRepository.save(product);
            return "redirect:/products";
        }catch (Exception ex){
            modelMap.addAttribute("error",ex.toString());
        }
        return "redirect:/products/getProductsByCategoryId/" + product.getCategoryId();
    }
    @RequestMapping(value = "/deleteProduct/{productId}",method = RequestMethod.POST)
    public String deleteProduct(@PathVariable("productId") String productId,ModelMap modelMap){
        productRepository.deleteById(productId);
        return "redirect:/products";
    }

}
