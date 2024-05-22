package org.example.livres.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.livres.HelloApplication;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private Label acceuil_LD;

    @FXML
    private Label acceuil_TM;

    @FXML
    private Label acceuil_TV;

    @FXML
    private Button acceuil_btn;

    @FXML
    private AnchorPane acceuil_form;

    @FXML
    private BarChart<?, ?> acceuil_statistiqueClient;

    @FXML
    private AreaChart<?, ?> acceuil_statistiqueRevenus;

    @FXML
    private Button close;

    @FXML
    private Button close1;

    @FXML
    private TextField livreDispo_Auteur;

    @FXML
    private DatePicker livreDispo_Date;

    @FXML
    private TextField livreDispo_Genre;

    @FXML
    private TextField livreDispo_ID;

    @FXML
    private TextField livreDispo_Prix;

    @FXML
    private TextField livreDispo_Titre;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Auteur;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Date;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Genre;

    @FXML
    private TableColumn<?, ?> livreDispo_col_ID;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Prix;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Titre;

    @FXML
    private AnchorPane livreDispo_form;

    @FXML
    private javafx.scene.image.ImageView livreDispo_imageView;

//    public ImageView livreDispo_imageView;

    @FXML
    private Button livreDispo_importBtn;

    @FXML
    private TextField livreDispo_search;

    @FXML
    private TableView<?> livreDispo_tableView;

    @FXML
    private Button livresDisponible_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<?> pannier_ID;

    @FXML
    private ComboBox<?> pannier_Titre;

    @FXML
    private Label pannier_Total;

    @FXML
    private Button pannier_addBtn;

    @FXML
    private Button pannier_btn;

    @FXML
    private TableColumn<?, ?> pannier_col_Auteur;

    @FXML
    private TableColumn<?, ?> pannier_col_Genre;

    @FXML
    private TableColumn<?, ?> pannier_col_ID;

    @FXML
    private TableColumn<?, ?> pannier_col_Prix;

    @FXML
    private TableColumn<?, ?> pannier_col_Quantité;

    @FXML
    private TableColumn<?, ?> pannier_col_Titre;

    @FXML
    private AnchorPane pannier_form;

    @FXML
    private Label pannier_info_Auteur;

    @FXML
    private Label pannier_info_Date;

    @FXML
    private Label pannier_info_Genre;

    @FXML
    private Label pannier_info_ID;

    @FXML
    private Label pannier_info_Titre;

    @FXML
    private Button pannier_payBtn;

    @FXML
    private TableView<?> pannier_tableView;

    @FXML
    private Label username;

    public void

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {

            // Alert pour confirmation de l'action
            Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
            alt.setTitle("Message de confirmation");
            alt.setHeaderText(null);
            alt.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");
            Optional<ButtonType> oooption = alt.showAndWait();

            if (oooption.get().equals(ButtonType.OK)) { // Condition si on clique sur le button OK

                // Pour cacher la fenêtre de la page d'acceuil
                logout.getScene().getWindow().hide();

                // Pour naviguer vers la fenêtre de connexion
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("connection.fxml")));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{ //Responsable de l'oppaciter l'orsqu'on traîne l'écran.
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT); // Pour faire disparaitre la petite bande en haut de l'écran

                stage.setTitle("Connection");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    // Methode qui permet de fermer la fenêtre
    public void close() {
        System.exit(0);
    }

    // Methode qui permet de reduire la fenêtre
    public void minimize() {
        Stage stg = (Stage) main_form.getScene().getWindow();
        stg.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
