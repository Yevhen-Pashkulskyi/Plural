package control;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utility.*;

import exc.MyException;
import model.PluralProduct;
import model.Product;

import javax.swing.*;

public class ControlApp {
    public void run() {
        PluralProduct pluralProduct = new PluralProduct();
        String choice = "";

        boolean done = true;
        while (done) {
            try {
                choice = showInputDialog(null, "1 create product\n2 show list product\n" +
                        "3 знайти продукти за ціною \n4 вивести максимально вигідний продукт\n0 exit");
                // перевіряємо якщо нічого не обрано та натиснули кнопки виходу та отмени виходимо з програми
                if (choice == null) {
                    break;
                } else if (choice.isEmpty()) {
                    showMessageDialog(null, "Зробіть вибір");
                    continue;
                }
                switch (choice) {
                    case "1": // add to list
                        pluralProduct.addProduct(createProduct());
                        break;
                    case "2": // print list
                        showMessageDialog(null, "Список продуктів:\n" + pluralProduct.printProducts());
                        break;
                    case "3": // show price retail more search price
                        String searchPriceStr = showInputDialog(null, "Вивести список продуктів більш за ціну: ");
                        showMessageDialog(null, pluralProduct.printProducts(pluralProduct.findProductForHigherPrice(searchPriceStr)));
                        break;
                    case "4": // max proceeds
                        String name = pluralProduct.findMaxPotentialRevenue();
                        showMessageDialog(null, name);
                        break;
                    case "0":
                        done = false;
                        break;
                    default:
                        showMessageDialog(null, "Невірний ввід");
                        break;
                }
            } catch (NullPointerException | MyException e) {
                showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // метод створює продукт з випадковими значеннями
    private Product createProduct() {
        Product product = null;
        try {
            String counterStr = String.valueOf(randomNumber(0, 1000));
            String nameProduct = /*showInputDialog(null, "Enter name product");*/"Product" + counterStr;
            if (nameProduct.length() < 3) {
                throw new MyException("Довжина продукту повинна бути більше 3 символів");
            }
            String manufacturerProduct = /*showInputDialog(null, "Enter manufacturer");*/"Manufacturer" + counterStr;
            if (manufacturerProduct.length() < 5) {
                throw new MyException("Довжина виробника має бути більш 5 символів");
            }
            int quantity = randomNumber(-10, 10);
            if (quantity < 0) {
                throw new MyException("Товар не може мінусовим");
            }
            int warranty = randomNumber(30, 60); //todo поменять от 0 до 365
            if (warranty < 30) {
                throw new MyException("Гарантійний період менше 30 днів");
            }
            double priceWholesale = /*Double.parseDouble(showInputDialog(null,"Оптова ціна"));*/rounderDouble(randomNumber(-10.0, 1000.0));
            double retailPrice = /*Double.parseDouble(showInputDialog(null,"Роздрібна ціна"));*/rounderDouble(priceWholesale * 1.3);
            if (priceWholesale > retailPrice) {
                throw new MyException("Оптова ціна не може бути більше за роздрібну");
            } else if (retailPrice == 0 || priceWholesale == 0) {
                throw new MyException("ціна не може дорівнювати 0");
            }
            product = new Product(nameProduct, manufacturerProduct, quantity, warranty, priceWholesale, retailPrice);
            return product;
        } catch (IllegalArgumentException e) {
            showMessageDialog(null, "Помилка во внесенні данних" + e.getMessage());
            return null;
        } catch (MyException e) {
            showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}
