package controllers;

import entities.shop.Product;
import services.shop.ProductServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class Product_Controller {

    @FXML private ListView<Product> productListView;
    @FXML private TextField productName;
    @FXML private TextField productPrice;
    @FXML private TextField productQuantity;
    @FXML private TextField productDescription;
    @FXML private TextField productCategory;
    @FXML private TextField productImage;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button refreshButton;

    private ProductServices productServices = new ProductServices();
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    // Méthode d'initialisation : remplit la ListView avec les produits
    @FXML
    public void initialize() {
        refreshProductList();

        // Ajouter un produit
        addButton.setOnAction(event -> addProduct());

        // Modifier un produit
        editButton.setOnAction(event -> editProduct());

        // Supprimer un produit
        deleteButton.setOnAction(event -> deleteProduct());

        // Rafraîchir la liste des produits
        refreshButton.setOnAction(event -> refreshProductList());

        // Sélectionner un produit dans la liste pour pré-remplir les champs de saisie
        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fillFormWithProduct(newValue);
            }
        });
    }

    // Rafraîchir la liste des produits dans la ListView
    private void refreshProductList() {
        try {
            List<Product> products = productServices.readList();
            productList.setAll(products);  // Remplir la ListView avec les produits
            productListView.setItems(productList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Ajouter un nouveau produit
    private void addProduct() {
        String name = productName.getText();
        double price = Double.parseDouble(productPrice.getText());
        int quantity = Integer.parseInt(productQuantity.getText());
        String description = productDescription.getText();
        String category = productCategory.getText();
        String image = productImage.getText();

        Product newProduct = new Product(name, price, quantity, description, image, 1, category, 0);

        try {
            productServices.create(newProduct);  // Appeler la méthode de création
            refreshProductList();  // Rafraîchir la liste après l'ajout
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Modifier un produit existant
    private void editProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            System.out.println("Aucun produit sélectionné pour la modification.");
            return;
        }

        selectedProduct.setName(productName.getText());
        selectedProduct.setPrice(Double.parseDouble(productPrice.getText()));
        selectedProduct.setQuantity(Integer.parseInt(productQuantity.getText()));
        selectedProduct.setDescription(productDescription.getText());
        selectedProduct.setCategory(productCategory.getText());
        selectedProduct.setImage(productImage.getText());

        productServices.update(selectedProduct);  // Pas besoin de try-catch si SQLException n'est pas déclarée
        refreshProductList();

    }

    // Supprimer un produit
    private void deleteProduct() {
        Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            System.out.println("Aucun produit sélectionné pour la suppression.");
            return;
        }

        try {
            productServices.delete(selectedProduct.getId());  // Appeler la méthode de suppression
            refreshProductList();  // Rafraîchir la liste après la suppression
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du produit : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Remplir les champs de saisie avec les informations d'un produit sélectionné
    private void fillFormWithProduct(Product product) {
        productName.setText(product.getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productQuantity.setText(String.valueOf(product.getQuantity()));
        productDescription.setText(product.getDescription());
        productCategory.setText(product.getCategory());
        productImage.setText(product.getImage());
    }
}