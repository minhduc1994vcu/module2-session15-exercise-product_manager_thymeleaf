package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class ProductController {
    private ProductService productService = new ProductServiceImpl();

    @GetMapping("/")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list";
    }

    @GetMapping("/products/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/products/create")
    public String createProduct(Product product, RedirectAttributes redirectAttributes) {
        ProductServiceImpl.setKey(ProductServiceImpl.getKey() + 1);
        product.setId(ProductServiceImpl.getKey());
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "New product was created!");
        return "redirect:/";
    }

    @GetMapping("/products/{id}/edit")
    public String editProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/products/edit")
    public String editProduct(Product product, RedirectAttributes redirectAttributes) {
        productService.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("message", "Product info was edited!");
        return "redirect:/";
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable int id, RedirectAttributes redirectAttributes) {
        productService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Product was deleted!");
        return "redirect:/";
    }

    @GetMapping("/products/{id}/view")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("products", product);
        return "view";
    }
}
