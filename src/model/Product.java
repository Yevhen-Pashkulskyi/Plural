package model;

import util.Utility;

import java.util.LinkedList;

public class Product {
    private String nameProduct;
    private String manufacturer; // виробник
    private int quantity;
    private int warrantyPeriod;
    private double wholesalePrice; // оптовая ціна
    private double retailPrice;

    private double proceedsWholesale;
    private double proceedsRetailPrice;

    public Product() {
    }

    public Product(String nameProduct, String manufacturer, int quantity, int warrantyPeriod,
                   double wholesalePrice, double retailPrice) {
        this.nameProduct = nameProduct;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
        this.proceedsWholesale = proceedsSale(quantity, wholesalePrice);
        this.proceedsRetailPrice = proceedsSale(quantity, retailPrice);
    }

    public LinkedList<Product> addProduct(Product product) {
        LinkedList<Product> products = new LinkedList<>();
        products.add(this);
        return products;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public double getProceedsWholesale() {
        return proceedsSale(quantity, wholesalePrice);
    }

    public double getProceedsRetailPrice() {
        return proceedsSale(quantity, retailPrice);
    }

    @Override
    public String toString() {
        return "nameProduct='" + nameProduct + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", quantity=" + quantity +
                ", warrantyPeriod=" + warrantyPeriod +
                ", Оптовая ціна=" + wholesalePrice +
                ", retailPrice=" + retailPrice +
                ", proceedsWholesale=" + proceedsWholesale +
                ", proceedsRetailPrice=" + proceedsRetailPrice;
    }

    private double proceedsSale(int quantity, double price) {
        double sum = price * quantity;
        return Utility.rounderDouble(sum);
    }
}
