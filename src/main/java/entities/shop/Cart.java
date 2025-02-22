package entities.shop;

import java.sql.Timestamp;
import java.util.List;

public class Cart {
    private int id;  // Identifiant unique du panier (auto-incrémenté dans la BD)
    private int userId; // ID de l'utilisateur associé
    private Timestamp dateCreated; // Date de création du panier
    private double totalPrice;  // Total du panier
    private List<Cartitem> items;  // Liste des articles dans le panier

    public Cart() {
        this.totalPrice = 0;  // Initialiser à 0
    }

    public Cart(int id, int userId, Timestamp dateCreated, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.totalPrice = totalPrice;
    }

    public Cart(int userId, Timestamp dateCreated, double totalPrice) {
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Cartitem> getItems() {
        return items;
    }

    public void setItems(List<Cartitem> items) {
        this.items = items;
        calculateTotalPrice();  // Recalculate total when items are updated
    }

    public void calculateTotalPrice() {
        totalPrice = 0;
        if (items != null) {
            for (Cartitem item : items) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateCreated=" + dateCreated +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }
}