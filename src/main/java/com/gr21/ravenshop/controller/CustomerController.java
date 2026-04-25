package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.dto.CustomerForm;
import com.gr21.ravenshop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", customerService.listCustomers());
        return "customers/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new CustomerForm());
        return "customers/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") CustomerForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customers/form";
        }

        customerService.createCustomer(
                form.getFullName(),
                form.getEmail(),
                form.getPhone(),
                form.getAddress()
        );
        return "redirect:/customers";
    }
}
