package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox; // Changer AnchorPane en VBox
import javafx.stage.Stage;

public class Main_Product_FX extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interface_product.fxml"));
            VBox root = loader.load(); // Modifier AnchorPane en VBox

            // Définir la scène avec le contenu du fichier FXML
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            // Titre de la fenêtre
            primaryStage.setTitle("Gestion des Produits");

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
