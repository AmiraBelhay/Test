module info {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;

    // Pour ouvrir vos packages à JavaFX, par exemple :
    opens controllers to javafx.fxml;


    exports main;

    opens main to javafx.fxml, javafx.graphics;
}
