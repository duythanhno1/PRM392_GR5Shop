package com.example.se1606_prm392_group05.Model;


import java.io.Serializable;

public class Cart implements Serializable {
    private int cartId;
    private Product product;
    private int quantity =1;

    public Cart(int cartId, Product product, int quantity) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getCartId() {
        return cartId;
    }

    public String getProductName() {
        if (product != null) {
            return product.getProductName();
        } else {
            return "";
        }
    }

    public double getProductPrice() {
        if (product != null) {
            return product.getProductPrice();
        } else {
            return 0.0;
        }
    }

    public String getProductImage() {
        if (product != null) {
            return product.getProductImage();
        } else {
            return "";
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}



