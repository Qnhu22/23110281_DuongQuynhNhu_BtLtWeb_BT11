package com.demo.security.controller;

import com.demo.security.entity.Product;
import com.demo.security.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    // Danh sách cho admin (có quản lý CRUD)
    @GetMapping
    public String listForAdmin(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin_products"; // admin_products.html
    }

    // Form thêm mới
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product"; // new_product.html
    }

    @PostMapping
    public String save(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Product p = productService.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", p);
        return "edit_product";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("product") Product product) {
        product.setId(id);
        productService.save(product);
        return "redirect:/admin/products";
    }

    // Xoá
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
