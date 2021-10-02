package hvl.dat152;

import hvl.dat152.database.DescriptionDAO;
import hvl.dat152.database.ProductDAO;
import hvl.dat152.model.Cart;
import hvl.dat152.model.Product;
import hvl.dat152.utils.CurrencyConverter;

import java.util.*;

public class JavaStoreApplication {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();
        DescriptionDAO descriptionDAO = new DescriptionDAO();

        //Locale locale = new Locale("nn", "NO");
        Locale locale = new Locale("en", "GB");
        // Locale locale = new Locale("es", "ES");

        ResourceBundle webstoretexts = ResourceBundle.getBundle("webstoretexts", locale);

        Cart cart = new Cart();
        Scanner in = new Scanner(System.in);

        System.out.println(webstoretexts.getString("locale") + ": " + locale);
        for (Product p : productDAO.getProductList()) {
            System.out.println(p + ": " +
                    descriptionDAO.getDescription(p.getPid(), locale.getLanguage())
                    + " ( " + CurrencyConverter.convertToCurrency(webstoretexts, p.getPriceInEuros()) + " )");
        }

        String input;

        while (true) {
                System.out.println(webstoretexts.getString("cart")+ ": " + cart);
                System.out.println(webstoretexts.getString("inputRequest") + ": ");

                // PLS DO NOT INPUT NON-EXISTING IDS
                input = in.nextLine();

                if (input.equals("esc")) return;

                cart.addProduct(productDAO.getProduct(Integer.parseInt(input)));
        }
    }
}
