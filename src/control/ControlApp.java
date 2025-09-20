package control;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utility.*;

import model.PluralProduct;
import model.Product;

public class ControlApp {
    public void run() {
        PluralProduct pluralProduct = new PluralProduct();
        String choice = "";

        boolean done = true;
        while (done) {
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
                    String counterStr = String.valueOf(randomNumber(0, 1000));
                    pluralProduct.addProduct(createProduct("Product" + counterStr, "Manufacturer" + counterStr));
                    showMessageDialog(null, "Create product" + counterStr + " successful");

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
            }
        }

    }

    // метод створює продукт з випадковими значеннями
    private Product createProduct(String nameProduct, String manufacturer) {
        double priceWholesale = rounderDouble(randomNumber(-10.0, 1000.0));
        return new Product(nameProduct, manufacturer, randomNumber(-10, 10), randomNumber(0, 365),
                priceWholesale, priceWholesale * 1.3);
    }
}
