package com.epam.model;

import java.util.List;

public class OrderRequest {

    private String id;
    private String email;
    private String status;
    private List<ProductItem> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "UpdateCartRequest{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            ", status='" + status + '\'' +
            ", products=" + products +
            '}';
    }
}
