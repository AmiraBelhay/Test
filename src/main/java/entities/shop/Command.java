package entities.shop;

import java.time.LocalDateTime;
import java.util.List;

public class Command {
    public enum Status {
        Pending("Pending"),
        Confirmed("Confirmed"),
        Shipped("Shipped"),
        Delivered("Delivered"),
        Canceled("Canceled");

        private final String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private int id;                // Identifiant de la commande (généré par la BD)
    private int cartId;            // Identifiant du panier
    private int userId;            // Identifiant de l'utilisateur
    private String paymentMethod;  // Méthode de paiement utilisée
    private List<Cartitem> items;  // Liste des articles dans la commande
    private Status status;         // Statut de la commande
    private LocalDateTime dateCreated; // Date de création de la commande

    /**
     * Constructeur pour créer une nouvelle commande.
     * L'id est généré par la base de données (auto-incrémenté).
     */
    public Command(int cartId, int userId, String paymentMethod, List<Cartitem> items) {
        this.id = 0; // Valeur par défaut (à remplacer par l'id généré par la BD lors de l'insertion)
        this.cartId = cartId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.status = Status.Pending;
        this.dateCreated = LocalDateTime.now();
    }

    /**
     * Constructeur pour récupérer une commande depuis la base de données.
     */
    public Command(int id, int cartId, int userId, LocalDateTime dateCreated, Status status, String paymentMethod) {
        this.id = id;
        this.cartId = cartId;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.status = status;
        this.paymentMethod = paymentMethod;
        // La liste d'articles n'est pas nécessairement récupérée ici.
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getCartId() {
        return cartId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<Cartitem> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    // Setter pour modifier le statut de la commande
    public void setStatus(Status status) {
        this.status = status;
    }

    // Calcule le prix total de la commande
    public double getTotalPrice() {
        double total = 0.0;
        if (items != null) {
            for (Cartitem item : items) {
                total += item.getPrice() * item.getQuantity(); // Assure-toi que getPrice() et getQuantity() existent
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Commande #" + id +
                " | Utilisateur: " + userId +
                " | Paiement: " + paymentMethod +
                " | Statut: " + status.getLabel() +
                " | Date: " + dateCreated +
                " | Total: " + getTotalPrice() + "€";
    }
}
