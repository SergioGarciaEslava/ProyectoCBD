package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.dto.ProductCreateRequest;
import com.gr21.ravenshop.dto.ProductForm;
import com.gr21.ravenshop.dto.ProductResponse;
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

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", productService.list(0, 50).items());
        return "products/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new ProductForm());
        model.addAttribute("edit", false);
        return "products/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") ProductForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", false);
            return "products/form";
        }
        productService.create(toRequest(form));
        return "redirect:/products";
    }

    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable String productId, Model model) {
        return productService.getById(productId)
                .map(product -> {
                    model.addAttribute("form", toForm(product));
                    model.addAttribute("edit", true);
                    model.addAttribute("productId", productId);
                    return "products/form";
                })
                .orElse("redirect:/products");
    }

    @PostMapping("/{productId}")
    public String update(
            @PathVariable String productId,
            @Valid @ModelAttribute("form") ProductForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("productId", productId);
            return "products/form";
        }
        productService.update(productId, toRequest(form));
        return "redirect:/products";
    }

    @PostMapping("/{productId}/delete")
    public String delete(@PathVariable String productId) {
        productService.deactivate(productId);
        return "redirect:/products";
    }

    private ProductCreateRequest toRequest(ProductForm form) {
        return new ProductCreateRequest(
                form.getName(),
                form.getDescription(),
                form.getCategory(),
                List.of(),
                form.getUnitPrice(),
                form.isActive()
        );
    }

    private ProductForm toForm(ProductResponse response) {
        ProductForm form = new ProductForm();
        form.setName(response.name());
        form.setDescription(response.description());
        form.setCategory(response.category());
        form.setUnitPrice(response.unitPrice());
        form.setActive(response.active());
        return form;
    }
}
