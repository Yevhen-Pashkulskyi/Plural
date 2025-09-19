package control;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utility.*;

import model.Product;

import java.util.LinkedList;

public class ControlApp {
    public void run() {
        LinkedList<Product> products = new LinkedList<>();
        String choice = "";

        boolean done = true;
        while (done) {
            choice = showInputDialog(null, "1 create product\n2 show list product\n" +
                    "3 знайти продукти за ціною \n0 exit");
            switch (choice) {
                case "1": // add to list
                    String counterStr = String.valueOf(randomNumber(0, 1000));
                    products.add(createProduct("Product" + counterStr, "Manufacturer" + counterStr));
                    showMessageDialog(null, "Create product" + counterStr + " successful");

                    break;
                case "2": // print list
                    StringBuilder builder = new StringBuilder(printProducts(products).toString());
                    showMessageDialog(null, "Список продуктів:\n" + printProducts(products));
                    break;
                case "3": // search for more price retail
                    String searchPriceStr = showInputDialog(null, "Вивести список продуктів більш за ціну: ");
                    showMessageDialog(null, printProducts(findForMorePricesRetail(products, searchPriceStr)));
                    break;
                case "0":
                    done = false;
                    break;
            }
        }

    }


    private Product createProduct(String nameProduct, String manufacturer) {
        double priceWholesale = rounderDouble(randomNumber(-10.0, 1000.0));
        return new Product(nameProduct, manufacturer, randomNumber(-10, 10), randomNumber(0, 365),
                priceWholesale, priceWholesale * 1.3);
    }

    private StringBuilder printProducts(LinkedList<Product> products) {
        StringBuilder output = new StringBuilder();
        for (Product product : products) {
            output.append(product.toString()).append("\n");
        }
        return output;
    }

    private LinkedList<Product> findForMorePricesRetail(LinkedList<Product> products, String searchprice) {
        LinkedList<Product> outList = new LinkedList<>();
        for (Product product : products) {
            if (product.getRetailPrice() > Double.parseDouble(searchprice)) {
                outList.add(product);
            }
        }
        return outList;
    }

    private String findMaxProceeds(LinkedList<Product> products) {
        String output;
        double maxProceeds;
        for (int i = 0; i < products.size(); i++) {
            products.get(i).getProceedsRetailPrice();
        }
    }//todo доделать сначала отсортировать потом найти максимальное значение и вывести продукт
}
