package essths.li3.clothingstore;

public class Product {
    private String name;
    private String brand;
    private double price;
    private int imageResource;
    private boolean isLiked;
    private String category;  // New category attribute

    public Product(String name, String brand, double price, int imageResource, boolean isLiked, String category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.imageResource = imageResource;
        this.isLiked = isLiked;
        this.category = category;
    }

    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public boolean isLiked() { return isLiked; }
    public String getCategory() { return category; }

    public void setLiked(boolean liked) { isLiked = liked; }
}