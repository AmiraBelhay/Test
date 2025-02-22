package entities.shop;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String image;
    private int id_discipline;
    private String category;
    private int numberOfPurchases;

    // Updated constructor with all fields
    public Product(int id, String name, double price, int quantity, String description, String image, int id_discipline, String category, int numberOfPurchases) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.id_discipline = id_discipline;
        this.category = category;
        this.numberOfPurchases = numberOfPurchases;
    }

    public Product(String name, double price, int quantity, String description, String image, int id_discipline, String category, int numberOfPurchases) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.id_discipline = id_discipline;
        this.category = category;
        this.numberOfPurchases = numberOfPurchases;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getIdDiscipline() {
        return id_discipline;
    }

    public String getCategory() {
        return category;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdDiscipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        this.numberOfPurchases = numberOfPurchases;
    }

    @Override
    public String toString() {
        return "Product {" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Price=" + price + "€" +
                ", Quantity=" + quantity +
                ", Description='" + description + '\'' +
                ", Image='" + image + '\'' +
                ", ID_Discipline=" + id_discipline +
                ", Category='" + category + '\'' +
                ", NumberOfPurchases=" + numberOfPurchases +
                '}';
    }
}
