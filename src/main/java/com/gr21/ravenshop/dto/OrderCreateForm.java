package com.gr21.ravenshop.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderCreateForm {

    private String customerId;
    private String shippingAddress;
    private List<OrderLineForm> lines = new ArrayList<>();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderLineForm> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineForm> lines) {
        this.lines = lines == null ? new ArrayList<>() : new ArrayList<>(lines);
    }
}
