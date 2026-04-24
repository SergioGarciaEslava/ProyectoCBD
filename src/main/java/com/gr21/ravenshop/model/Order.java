package com.gr21.ravenshop.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private String id;
    private String customerId;
    private CustomerSnapshot customerSnapshot;
    private OffsetDateTime orderedAt;
    private String status;
    private String shippingAddress;

    @JsonAlias("lines")
    private List<OrderLineItem> lineItems = new ArrayList<>();

    @JsonAlias("total")
    private BigDecimal total;

    private List<StatusHistoryEntry> statusHistory = new ArrayList<>();

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public CustomerSnapshot getCustomerSnapshot() {
        return customerSnapshot;
    }

    public void setCustomerSnapshot(CustomerSnapshot customerSnapshot) {
        this.customerSnapshot = customerSnapshot;
    }

    public OffsetDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(OffsetDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems == null ? new ArrayList<>() : new ArrayList<>(lineItems);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<StatusHistoryEntry> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistoryEntry> statusHistory) {
        this.statusHistory = statusHistory == null ? new ArrayList<>() : new ArrayList<>(statusHistory);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomerSnapshot {

        private String customerId;
        private String fullName;
        private String email;
        private String city;

        public CustomerSnapshot() {
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderLineItem {

        private String productId;
        private String productName;
        private int quantity;
        private BigDecimal unitPrice;
        private BigDecimal lineTotal;

        public OrderLineItem() {
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getLineTotal() {
            if (lineTotal != null) {
                return lineTotal;
            }
            if (unitPrice == null) {
                return null;
            }
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }

        public void setLineTotal(BigDecimal lineTotal) {
            this.lineTotal = lineTotal;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatusHistoryEntry {

        private String status;
        private OffsetDateTime changedAt;

        public StatusHistoryEntry() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public OffsetDateTime getChangedAt() {
            return changedAt;
        }

        public void setChangedAt(OffsetDateTime changedAt) {
            this.changedAt = changedAt;
        }
    }
}
