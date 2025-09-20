package model;

import exc.MyException;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import static javax.swing.JOptionPane.showMessageDialog;

public class PluralProduct {
    private LinkedList<Product> products;

    public PluralProduct() {
        products = new LinkedList<>();
    }

    //метод додає в список продукт
    public void addProduct(Product product) throws MyException {
        if (product == null) {
            throw new NullPointerException("Продукт не створенний");
        }
        for (Product p : products) {
            if (p.getNameProduct().equals(product.getNameProduct()) && p.getManufacturer().equals(product.getManufacturer())) {
                throw new MyException("Product name and Manufacturer are the same");
            }
        }
        products.add(product);
        showMessageDialog(null, "Create:\n" + product.getNameProduct() + "\nsuccessful");
    }

    // метод створює масив символів кожного продукту та повертає його
    public StringBuilder printProducts() {
        checkListProductIsEmpty();
        StringBuilder output = new StringBuilder();
        try {
            for (Product product : products) {
                output.append(product.toString()).append("\n");
            }
            return output;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Список пуст", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
        checkListProductIsEmpty();
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
        checkListProductIsEmpty();
        return Collections.max(products, Comparator.comparing(Product::getRevenueRetailPrice)).getNameProduct();
    }

    // метод перевіряє на заповненість списку
    private void checkListProductIsEmpty() {
        if (products.isEmpty() || products == null) {
            throw new NullPointerException("Список пуст!");
        } else {
return;
        }
    }
}
