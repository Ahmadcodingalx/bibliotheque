module org.example.livres {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.livres to javafx.fxml;
    exports org.example.livres;

    opens org.example.livres.Controllers to javafx.fxml;
    exports org.example.livres.Controllers;
}