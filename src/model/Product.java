package model;

import exc.MyException;
import util.Utility;

import java.util.Objects;

public class Product implements Comparable<Product> {
    private String nameProduct;
    private String manufacturer; // виробник
    private int quantity;
    private int warrantyPeriod;
    private double wholesalePrice; // оптовая ціна
    private double retailPrice;


    public Product(String nameProduct, String manufacturer, int quantity, int warrantyPeriod,
                   double wholesalePrice, double retailPrice) throws MyException {
        this.nameProduct = nameProduct;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.warrantyPeriod = warrantyPeriod;
        this.wholesalePrice = wholesalePrice;
        this.retailPrice = retailPrice;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public double getProfit() {
        return Utility.rounderDouble((retailPrice - wholesalePrice) * quantity);
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
                ", Прибуток = " + getProfit();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct) && Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameProduct, manufacturer);
    }

    @Override
    public int compareTo(Product other) {
        return Double.compare(this.getProfit(), other.getProfit());
    }
}
