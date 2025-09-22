package control;

import exc.MyException;
import model.ProductCollection;
import model.Product;

import javax.swing.*;

import static javax.swing.JOptionPane.*;

public class ControlApp {
    ProductCollection productCollection = new ProductCollection();

    public void run() {

        String choice = "";

        while (true) {
            try {
                choice = showInputDialog(null,
                        "1. Створити продукт\n" +
                                "2. Показати список товарів\n" +
                                "3. Знайти товар більше за ціну\n" +
                                "4. Знайти товар з максимальним прибутком");
                // перевіряємо якщо нічого не обрано та натиснули кнопки виходу та отмени виходимо з програми
                if (choice == null) {
                    break;
                } else if (choice.isEmpty()) {
                    showMessageDialog(null, "Зробіть вибір");
                    continue;
                }
                switch (choice) {
                    case "1": // add to list
                        fillData();
                        break;
                    case "2": // print list
                        showMessageDialog(null, "Список продуктів:\n" + productCollection.printProducts());
                        break;
                    case "3": // show price retail more search price
                        productCollection.checkListProductIsEmpty();
                        String searchPriceStr = showInputDialog(null, "Введіть ціну: ");
                        showMessageDialog(null, productCollection.printProducts(productCollection.findProductsByPriceHigherThen(searchPriceStr)));
                        break;
                    case "4": // max proceeds
                        String name = productCollection.findMaxProfit();
                        showMessageDialog(null, name);
                        break;
                    default:
                        showMessageDialog(null, "Невірний ввід", "Помилка", WARNING_MESSAGE);
                        break;
                }
            } catch (NullPointerException | MyException e) {
                showMessageDialog(null, e.getMessage(), "Помилка", WARNING_MESSAGE);
            }
        }

    }

    // метод створює продукт
    private void fillData() throws MyException {
        Product product = null;
        try {
//            String counterStr = String.valueOf(randomNumber(0, 1000));
            String nameProduct = showInputDialog(null, "Введіть назву продукту");//"Product" + counterStr;
            if (nameProduct == null || nameProduct.length() < 3) {
                throw new MyException("Назва товару має бути щонайменше 3 символи");
            }
            String manufacturerProduct = showInputDialog(null, "Введіть виробника");//"Manufacturer" + counterStr;
            if (manufacturerProduct == null || manufacturerProduct.length() < 5) {
                throw new MyException("Назва виробника має бути щонайменьше 5 символів");
            }
            productCollection.findByNameAndManufacturer(nameProduct, manufacturerProduct);
            int quantity = Integer.parseInt(showInputDialog(null, "Введіть кількість товару"));//randomNumber(-10, 10);
            if (quantity < 0) {
                throw new MyException("Кількість товару не можу бути від'ємною");
            }
            int warranty = Integer.parseInt(showInputDialog(null, "Введіть гарантійний строк"));//randomNumber(0, 365);
            if (warranty < 30) {
                throw new MyException("Гарантйний період меньше 30 днів");
            }
            double priceWholesale = Double.parseDouble(showInputDialog(null, "Оптова ціна"));//rounderDouble(randomNumber(-10.0, 1000.0));
            if (priceWholesale <= 0.0) {
                throw new MyException("Оптова ціна має бути більше 0");
            }
            double retailPrice = Double.parseDouble(showInputDialog(null, "Роздрібна ціна"));//rounderDouble(priceWholesale * 1.3);
            if (retailPrice <= 0.0) {
                throw new MyException("Роздрібна ціна має бути більше 0");
            }
            if (priceWholesale > retailPrice) {
                throw new MyException("Оптова ціна не може бути більшою за роздрібну");
            }
            product = new Product(nameProduct, manufacturerProduct, quantity, warranty, priceWholesale, retailPrice);
            productCollection.addProduct(product);
            showMessageDialog(null, "Товар додано " + product.getNameProduct().toUpperCase());
        } catch (NumberFormatException e) {
            throw new MyException("Невірний формат введених данних");
        }
    }
}
