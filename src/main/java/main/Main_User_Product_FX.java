package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane; // ou VBox selon votre FXML
import javafx.stage.Stage;

public class Main_User_Product_FX extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML de l'interface utilisateur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interface_user_product.fxml"));
            BorderPane root = loader.load(); // ou VBox, en fonction de votre fichier FXML

            // Définir la scène avec le contenu du fichier FXML
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            // Titre de la fenêtre
            primaryStage.setTitle("Interface Utilisateur - Produits");

            // Afficher la fenêtre principale
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Lancer l'application JavaFX
        launch(args);
    }
}
