package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.dto.ProductForm;
import com.gr21.ravenshop.model.Product;
import com.gr21.ravenshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.listProducts());
        return "products/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new ProductForm());
        addFormMode(model, false, null);
        return "products/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") ProductForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addFormMode(model, false, null);
            return "products/form";
        }

        productService.createProduct(
                form.getName(),
                form.getCategory(),
                form.getPrice(),
                form.getStock(),
                toTags(form.getTagsText())
        );
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        return productService.findProductById(id)
                .map(product -> {
                    model.addAttribute("form", toForm(product));
                    addFormMode(model, true, id);
                    return "products/form";
                })
                .orElse("redirect:/products");
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @Valid @ModelAttribute("form") ProductForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addFormMode(model, true, id);
            return "products/form";
        }

        productService.updateProduct(
                id,
                form.getName(),
                form.getCategory(),
                form.getPrice(),
                form.getStock(),
                toTags(form.getTagsText())
        );

        return "redirect:/products";
    }

    private List<String> toTags(String tagsText) {
        if (tagsText == null || tagsText.isBlank()) {
            return List.of();
        }

        return Arrays.stream(tagsText.split(","))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .toList();
    }

    private ProductForm toForm(Product product) {
        ProductForm form = new ProductForm();
        form.setName(product.getName());
        form.setCategory(product.getCategory());
        form.setPrice(product.getPrice());
        form.setStock(product.getStock());
        form.setTagsText(product.getTags() == null ? "" : String.join(", ", product.getTags()));
        return form;
    }

    private void addFormMode(Model model, boolean edit, String productId) {
        model.addAttribute("edit", edit);
        model.addAttribute("productId", productId);
    }
}
