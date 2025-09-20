package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class PluralProduct {
    private LinkedList<Product> products;

    public PluralProduct() {
        products = new LinkedList<>();
    }

    //метод додає в список продукт
    public void addProduct(Product product) {
        products.add(product);
    }

    // метод створює масив символів кожного продукту та повертає його
    public StringBuilder printProducts() {
        StringBuilder output = new StringBuilder();
        for (Product product : products) {
            output.append(product.toString()).append("\n");
        }
        return output;
    }

    // метод створює масив символів кожного продукту та повертає його
    public StringBuilder printProducts(LinkedList<Product> products) {
        StringBuilder output = new StringBuilder();
        for (Product product : products) {
            output.append(product.toString()).append("\n");
        }
        return output;
    }

    // метод знаходить та виводить список який більше за значену ціну
    public LinkedList<Product> findProductForHigherPrice(String price) {
        LinkedList<Product> outList = new LinkedList<>();
        for (Product product : products) {
            if (product.getRetailPrice() > Double.parseDouble(price)) {
                outList.add(product);
            }
        }
        return outList;
    }

    // метод шукає максимальне значення з списку використовуючи інтерфейс Collection і Comparator методом getNameProduct виводжу назву продукту
    public String findMaxPotentialRevenue() {
        return Collections.max(products, Comparator.comparing(Product::getRevenueRetailPrice)).getNameProduct();
    }

    // метод перевіряє на заповненість списку
    private boolean checkListProductIsEmpty(LinkedList<Product> products) {
        if (products.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
