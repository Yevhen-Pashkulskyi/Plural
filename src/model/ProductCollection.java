package model;

import exc.MyException;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import static javax.swing.JOptionPane.showMessageDialog;

public class ProductCollection {
    private LinkedList<Product> productsList;

    public ProductCollection() {
        productsList = new LinkedList<>();
    }

    //метод додає в список продукт
    public void addProduct(Product product) throws MyException {
        if (product == null) {
            throw new MyException("Товар не може бути null");
        }
        findByNameAndManufacturer(product.getNameProduct(), product.getManufacturer());
        productsList.add(product);
    }

    // метод створює масив символів кожного продукту та повертає його

    public StringBuilder printProducts() throws MyException {
        checkListProductIsEmpty();
        StringBuilder output = new StringBuilder();
        for (Product product : productsList) {
            output.append(product.toString()).append("\n");
        }
        return output;
    }

    public StringBuilder printProducts(LinkedList products) throws MyException {
        checkListProductIsEmpty();
        StringBuilder output = new StringBuilder();
        for (Object product : products) {
            output.append(product.toString()).append("\n");
        }
        return output;
    }
    // метод знаходить та виводить список який більше за значену ціну

    public LinkedList<Product> findProductsByPriceHigherThen(String priceStr) throws MyException {
        checkListProductIsEmpty();
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) {
                throw new MyException("Ціна має бути більше 0");
            }
        } catch (NumberFormatException e) {
            throw new MyException("Невірний формат ціни");
        }

        LinkedList<Product> outList = new LinkedList<>();
        for (Product product : productsList) {
            if (product.getRetailPrice() > price) {
                outList.add(product);
            }
        }
        return outList;
    }
    // метод шукає максимальне значення з списку використовуючи інтерфейс Collection і Comparator методом getNameProduct виводжу назву продукту

    public String findMaxProfit() throws MyException {
        checkListProductIsEmpty();
        return Collections.max(productsList).getNameProduct() + " = " + Collections.max(productsList).getProfit();
    }
    // метод перевіряє на заповненість списку

    public void checkListProductIsEmpty() throws MyException {
        if (productsList == null || productsList.isEmpty()) {
            throw new MyException("Список пуст!");
        }
    }

    public void findByNameAndManufacturer(String productName, String manufacturer) throws MyException {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getNameProduct().equals(productName) &&
                    productsList.get(i).getManufacturer().equals(manufacturer)) {
                throw new MyException("З такою назвою та виробником товар вже є");
            }
        }
    }
}
