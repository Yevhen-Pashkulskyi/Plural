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

    private double revenueWholesale;
    private double revenueRetailPrice;

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
        this.revenueWholesale = revenueSale(quantity, wholesalePrice);
        this.revenueRetailPrice = revenueSale(quantity, retailPrice);
    }

    public LinkedList<Product> addProduct(Product product) {
        LinkedList<Product> products = new LinkedList<>();
        products.add(this);
        return products;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public double getRevenueWholesale() {
        return revenueSale(quantity, wholesalePrice);
    }

    public double getRevenueRetailPrice() {
        return revenueSale(quantity, retailPrice);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Назва: " + nameProduct +
                ", Виробник: " + manufacturer +
                ", кількість =" + quantity +
                ", гарантійний період: " + warrantyPeriod +
                ", оптовая ціна = " + wholesalePrice +
                ", роздібна ціна = " + retailPrice +
                ", оптовий потенційний дохід = " + revenueWholesale +
                ", роздрібний потенційний дохід = " + revenueRetailPrice;
    }

    private double revenueSale(int quantity, double price) {
        double sum = price * quantity;
        return Utility.rounderDouble(sum);
    }
}
