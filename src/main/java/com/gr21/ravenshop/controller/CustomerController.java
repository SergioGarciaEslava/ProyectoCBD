package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.dto.CustomerForm;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        addFormMode(model, false, null);
        return "customers/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("form") CustomerForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addFormMode(model, false, null);
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

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        return customerService.findCustomerById(id)
                .map(customer -> {
                    model.addAttribute("form", toForm(customer));
                    addFormMode(model, true, id);
                    return "customers/form";
                })
                .orElse("redirect:/customers");
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @Valid @ModelAttribute("form") CustomerForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addFormMode(model, true, id);
            return "customers/form";
        }

        customerService.updateCustomer(
                id,
                form.getFullName(),
                form.getEmail(),
                form.getPhone(),
                form.getAddress()
        );
        return "redirect:/customers";
    }

    private CustomerForm toForm(Customer customer) {
        CustomerForm form = new CustomerForm();
        form.setFullName(customer.getFullName());
        form.setEmail(customer.getEmail());
        form.setPhone(customer.getPhone());
        form.setAddress(customer.getAddress());
        return form;
    }

    private void addFormMode(Model model, boolean edit, String customerId) {
        model.addAttribute("edit", edit);
        model.addAttribute("customerId", customerId);
    }
}
