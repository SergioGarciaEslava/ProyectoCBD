package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.dto.OrderCreateForm;
import com.gr21.ravenshop.dto.OrderLineForm;
import com.gr21.ravenshop.service.CustomerService;
import com.gr21.ravenshop.service.OrderService;
import com.gr21.ravenshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final int DEFAULT_FORM_LINES = 3;

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public String list(@RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "customer", required = false) String customer,
            @RequestParam(value = "minTotal", required = false) String minTotal,
            Model model) {
        model.addAttribute("orders", orderService.listOrders(status, customer, minTotal));
        model.addAttribute("filterStatus", status);
        model.addAttribute("filterCustomer", customer);
        model.addAttribute("filterMinTotal", minTotal);
        return "orders/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        if (!model.containsAttribute("orderForm")) {
            model.addAttribute("orderForm", emptyForm());
        }
        addFormOptions(model);
        return "orders/form";
    }

    @PostMapping
    public String create(@ModelAttribute("orderForm") OrderCreateForm form, Model model, RedirectAttributes redirectAttributes) {
        try {
            var order = orderService.createOrder(form);
            return "redirect:/orders/" + order.getId().replace("orders/", "");
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("orderForm", padLines(form));
            addFormOptions(model);
            return "orders/form";
        }
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model) {
        var order = orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        model.addAttribute("order", order);
        return "orders/detail";
    }

    private void addFormOptions(Model model) {
        model.addAttribute("customers", customerService.listCustomers());
        model.addAttribute("products", productService.listProducts());
    }

    private OrderCreateForm emptyForm() {
        return padLines(new OrderCreateForm());
    }

    private OrderCreateForm padLines(OrderCreateForm form) {
        List<OrderLineForm> lines = new ArrayList<>(form.getLines());
        while (lines.size() < DEFAULT_FORM_LINES) {
            lines.add(new OrderLineForm());
        }
        form.setLines(lines);
        return form;
    }
}
