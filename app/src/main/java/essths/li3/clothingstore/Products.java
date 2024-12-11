package essths.li3.clothingstore;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private static List<Product> products;

    public static List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
            products.add(new Product("Sérum Niacinamide", "Algo Vita", 37.0, R.drawable.niacinamide, false, "Hair"));
            products.add(new Product("Ecran Teinté", "PHYTEAL", 42.8, R.drawable.phyteal, false, "Face"));
            products.add(new Product("Lotion Clarifiante", "Herbeos", 39.0, R.drawable.herbeo, false, "Face"));
        }
        return products;
    }

    public static List<Product> getLikedProducts() {
        List<Product> likedProducts = new ArrayList<>();
        if (products != null) {
            for (Product product : products) {
                if (product.isLiked()) {
                    likedProducts.add(product);
                }
            }
        }
        return likedProducts;
    }

    public static List<Product> searchProducts(String query, String category) {
        List<Product> searchResults = new ArrayList<>();
        List<Product> allProducts = getProducts();  // Ensure products are initialized

        for (Product product : allProducts) {
            boolean matchesQuery = product.getName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getBrand().toLowerCase().contains(query.toLowerCase());

            if (matchesQuery && (category == null || category.isEmpty() ||
                    product.getCategory().equalsIgnoreCase(category))) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    public static List<Product> searchProducts(String query) {
        return searchProducts(query, null);
    }
}