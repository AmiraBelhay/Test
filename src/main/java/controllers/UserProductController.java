package controllers;

import entities.shop.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.shop.ProductServices;

import java.sql.SQLException;
import java.util.List;

public class UserProductController {

    @FXML private ListView<Product> productListView;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private Label quantityLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label categoryLabel;
    @FXML private Button addToCartButton;
    @FXML private Button detailsButton;

    private ProductServices productServices = new ProductServices();
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Chargement des produits pour l'utilisateur
        refreshProductList();

        // Lorsqu'un produit est sélectionné, on affiche ses détails
        productListView.getSelectionModel().selectedItemProperty().addListener((obs, oldProduct, newProduct) -> {
            if (newProduct != null) {
                displayProductDetails(newProduct);
            }
        });

        // Action pour "Ajouter au panier"
        addToCartButton.setOnAction(event -> {
            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Logique d'ajout au panier
                System.out.println("Produit ajouté au panier : " + selectedProduct.getName());
                // Vous pouvez intégrer ici votre service panier / commande
            }
        });

        // Action pour afficher plus de détails dans une nouvelle fenêtre par exemple
        detailsButton.setOnAction(event -> {
            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Ouvrir une nouvelle fenêtre avec des détails supplémentaires
                System.out.println("Afficher les détails complets pour : " + selectedProduct.getName());
            }
        });
    }

    private void refreshProductList() {
        try {
            List<Product> products = productServices.readList();
            productList.setAll(products);
            productListView.setItems(productList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayProductDetails(Product product) {
        nameLabel.setText("Nom : " + product.getName());
        priceLabel.setText("Prix : " + product.getPrice() + " €");
        quantityLabel.setText("Quantité : " + product.getQuantity());
        descriptionLabel.setText("Description : " + product.getDescription());
        categoryLabel.setText("Catégorie : " + product.getCategory());
    }
}
