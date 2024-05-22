package org.example.livres.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.net.URL;
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
    private ImageView livreDispo_imageView;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
