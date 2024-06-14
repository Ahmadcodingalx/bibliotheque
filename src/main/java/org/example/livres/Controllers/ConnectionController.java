package org.example.livres.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.livres.HelloApplication;
import org.example.livres.database;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.sql.Connection;

public class ConnectionController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private Button loginbutton;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox passwordShow;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Connection connection;
    private PreparedStatement prepStat;
    private ResultSet resultSet;

    private double x = 0;
    private double y = 0;

    public void AdminLogin() {

        String sqlReq = "SELECT * FROM admin_info WHERE username = ? AND password = ?";
        connection = database.connectDB();

        try {
            Alert alert;

            assert connection != null;
            prepStat = connection.prepareStatement(sqlReq);
            prepStat.setString(1, username.getText());
            prepStat.setString(2, password.getText());

            resultSet = prepStat.executeQuery();

            if (username.getText().isEmpty() || password.getText().isEmpty()) { // si on ne rempli pas l'un des deux champ

                //Une alerte de type ERREUR
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait, remplir tout les champs");
                alert.showAndWait();

            } else if (resultSet.next()) { // si le mot de passe et le nom d'utilisateur sont corrects

                //Une alerte de type INFORMATION
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message d'information");
                alert.setHeaderText(null);
                alert.setContentText("Connexion réussie!");
                alert.showAndWait();

                loginbutton.getScene().getWindow().hide(); //Pour cacher la fenêtre

                //Pour fermer la fenêtre
                Stage initial = (Stage) password.getScene().getWindow();
                initial.close();

                // Liaison de la fenêtre d'acceuil
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("acceuil.fxml")));
                Stage stg = new Stage();

                root.setOnMousePressed((MouseEvent evenement) -> {
                    x = evenement.getSceneX();
                    y = evenement.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stg.setX(event.getScreenX() -x);
                    stg.setY(event.getScreenY() -y);
                });

                stg.initStyle(StageStyle.TRANSPARENT);

                Scene scn = new Scene(root);
                stg.setScene(scn);
                stg.show();

            } else { // si le mot de passe et le nom d'utilisateur ne sont pas corrects

                //Une alerte de type ERREUR
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("nom d'utilisateur ou mot de passe incorrect");
                alert.showAndWait();

            }

        } catch (SQLException | IOException e ) {
            throw new RuntimeException(e);
        }

    }

    public void AdminLoginkey(KeyEvent clique) {

        if (clique.getCode() == KeyCode.ENTER) {
            AdminLogin(); //La methode s'execute l'orsqu'on appuie sur la touche ENTRER
        }
        
    }

//    public void passwordShow() {
//        password.setManagedPasswordVisible(true);
//        password.setDisable(!passwordShow.isSelected());
//    }

    public void close() {      // Methode qui permet de fermer la fenêtre l'orsqu'on clique sur le button
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
